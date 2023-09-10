package com.hst.studytool.domain;

public record Study(
        String key,
        String bookName,
        String bookDescription,
        String tableOfContents
) {}
