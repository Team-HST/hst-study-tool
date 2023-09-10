package com.hst.studytool.adapter.in.web;

import com.hst.studytool.application.command.StudyUpdateCommand;

public record StudyUpdateRequest (
        String bookName,
        String bookDescription,
        String tableOfContents
) {
    public StudyUpdateCommand toCommand(String id) {
        return new StudyUpdateCommand(id, bookName, bookDescription, tableOfContents);
    }
}
