package com.rostertwo.urlshortener.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Url implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_url")
    @SequenceGenerator(name = "generator_url", sequenceName = "seq_url", allocationSize = 1)
    private long id;

    @NotBlank
    private String shortUrl;

    @NotBlank
    private String longUrl;

    @NotNull
    OffsetDateTime dateCreated;

    OffsetDateTime dateExpired;
}
