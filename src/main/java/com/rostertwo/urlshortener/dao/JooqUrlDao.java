package com.rostertwo.urlshortener.dao;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Component("urlDao")
@RequiredArgsConstructor
public class JooqUrlDao implements UrlDao {

    private final DSLContext jooq;
}
