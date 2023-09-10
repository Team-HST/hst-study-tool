package com.hst.studytool.application;

import com.hst.studytool.domain.Study;
import com.hst.studytool.domain.StudyReview;
import com.hst.studytool.domain.port.in.StudyQueryUseCase;
import com.hst.studytool.domain.port.out.StudyReadPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyQueryService implements StudyQueryUseCase {

    private final StudyReadPort studyReadPort;

    public StudyQueryService(StudyReadPort studyReadPort) {
        this.studyReadPort = studyReadPort;
    }

    @Override
    public List<Study> getAllStudies() {
        return studyReadPort.findAll();
    }

    @Override
    public Study getStudy(String id) {
        return studyReadPort.findOne(id)
                .orElseThrow(() -> new IllegalArgumentException("스터디를 찾을 수 없음. id: " + id));
    }

    @Override
    public List<StudyReview> getStudyReviews(String id) {
        return studyReadPort.findStudyReviews(id);
    }
}
