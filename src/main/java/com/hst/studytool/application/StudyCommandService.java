package com.hst.studytool.application;

import com.hst.studytool.application.command.StudyUpdateCommand;
import com.hst.studytool.domain.Study;
import com.hst.studytool.domain.port.in.StudyCommandUseCase;
import com.hst.studytool.domain.port.out.StudyReadPort;
import com.hst.studytool.domain.port.out.StudyWritePort;
import org.springframework.stereotype.Service;

@Service
public class StudyCommandService implements StudyCommandUseCase {

    private final StudyReadPort studyReadPort;
    private final StudyWritePort studyWritePort;

    public StudyCommandService(StudyReadPort studyReadPort, StudyWritePort studyWritePort) {
        this.studyReadPort = studyReadPort;
        this.studyWritePort = studyWritePort;
    }

    @Override
    public void updateStudy(StudyUpdateCommand command) {
        Study study = studyReadPort.findOne(command.id()).orElseGet(command::toDomain);
        studyWritePort.save(study);
    }
}
