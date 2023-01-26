package com.rostertwo.urlshortener.controllers;

import com.rostertwo.urlshortener.dto.CreateUrlRequest;
import com.rostertwo.urlshortener.services.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String convertToShortUrl(@RequestBody CreateUrlRequest request) {
        return urlService.convertToShortUrl(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(urlService.findByShortUrl(id)))
                .build();
    }
}
