package com.hst.studytool.domain.port.in;

import com.hst.studytool.application.command.StudyUpdateCommand;

public interface StudyCommandUseCase {

    void updateStudy(StudyUpdateCommand command);

}
