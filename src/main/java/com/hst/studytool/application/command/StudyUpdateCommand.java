package com.hst.studytool.application.command;

import com.hst.studytool.domain.Study;

public record StudyUpdateCommand(
        String id,
        String bookName,
        String bookDescription,
        String tableOfContents
) {

    public Study toDomain() {
        return new Study(id, bookName, bookDescription, tableOfContents);
    }

}
