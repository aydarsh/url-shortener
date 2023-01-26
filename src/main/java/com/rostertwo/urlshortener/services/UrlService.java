package com.rostertwo.urlshortener.services;

import com.rostertwo.urlshortener.domain.Url;
import com.rostertwo.urlshortener.dto.CreateUrlRequest;
import com.rostertwo.urlshortener.repositories.UrlRepository;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.zip.CRC32;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;

@Service
@Transactional
@Slf4j
public class UrlService {

    private static final int SALT_SIZE = 8;
    private static final int SHORT_URL_LENGTH = 7;
    private final UrlRepository urlRepository;

    private final SecureRandom random;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
        random = new SecureRandom();
    }

    public String convertToShortUrl(CreateUrlRequest request) {
        CRC32 crc32 = new CRC32();
        byte[] salt = new byte[SALT_SIZE];
        ByteBuffer bbuffer = ByteBuffer.allocate(SALT_SIZE + SHORT_URL_LENGTH);

        String longUrl = request.getLongUrl();
        String shortUrl = createShortUrl(longUrl.getBytes(), crc32);

        while (urlRepository.existsByShortUrl(shortUrl)) {
            random.nextBytes(salt);
            bbuffer.put(salt);
            bbuffer.put(SALT_SIZE, shortUrl.getBytes());
            shortUrl = createShortUrl(bbuffer.array(), crc32);
        }

        log.info("Save URL: longUrl={}, shortUrl={}", longUrl, shortUrl);
        Url url = Url.builder()
                .longUrl(longUrl)
                .shortUrl(shortUrl)
                .dateCreated(OffsetDateTime.now())
                .dateExpired(request.getDateExpired())
                .build();
        Url entity = urlRepository.save(url);

        return entity.getShortUrl();
    }

    public String findByShortUrl(String shortUrl) {
        Url entity = urlRepository.findByShortUrl(shortUrl)
            .orElseThrow(() -> new EntityNotFoundException("Entity not found: " + shortUrl));

        if (entity.getDateExpired() != null && entity.getDateExpired().isBefore(OffsetDateTime.now())) {
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Link expired: " + shortUrl);
        }

        return entity.getLongUrl();
    }

    private String createShortUrl(byte[] longUrlByteArray, CRC32 crc32) {
        crc32.update(longUrlByteArray);
        return Long.toHexString(crc32.getValue()).substring(0,7);
    }
}
