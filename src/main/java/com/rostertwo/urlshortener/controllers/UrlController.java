package com.rostertwo.urlshortener.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    @PostMapping("/data/shorten")
    public ResponseEntity<HttpStatus> convertToShortUrl(@RequestBody String longUrl) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getAndRedirect(@PathVariable String shortUrl) {
        return ResponseEntity.ok("Hello " + shortUrl);
    }
}
