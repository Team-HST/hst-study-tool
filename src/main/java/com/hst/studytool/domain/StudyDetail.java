package com.hst.studytool.domain;

import java.util.List;

public record StudyDetail(
        String key,
        String bookName,
        String bookDescription,
        String tableOfContents,
        List<StudyReview> reviews
) {}
