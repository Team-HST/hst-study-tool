package com.hst.studytool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record StudyReview(
        List<String> participants,
        @JsonFormat(shape = JsonFormat.Shape.STRING) LocalDate studyDate,
        int startPage,
        int endPage,
        String comment
) {}
