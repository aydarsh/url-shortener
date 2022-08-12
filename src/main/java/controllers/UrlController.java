package controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    @PostMapping("/data/shorten")
    public String convertToShortUrl(@RequestBody String longUrl) {
        return "Hello from convertToShortUrl";
    }

    @GetMapping("/{shortUrl}")
    public String getAndRedirect(@PathVariable String shortUrl) {
        return "Hello from getAndRedirect";
    }
}
