package com.rostertwo.urlshortener.repositories;

import com.rostertwo.urlshortener.domain.Url;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

  Optional<Url> findByShortUrl(String shortUrl);

  boolean existsByShortUrl(String shortUrl);
}
