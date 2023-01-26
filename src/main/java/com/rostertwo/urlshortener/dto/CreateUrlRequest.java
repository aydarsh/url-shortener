package com.rostertwo.urlshortener.dto;

import java.time.OffsetDateTime;
import lombok.Value;

@Value
public class CreateUrlRequest implements DataTransferObject {
    String longUrl;
    OffsetDateTime dateExpired;
}
