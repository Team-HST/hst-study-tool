package com.hst.studytool.domain.port.in;

import com.hst.studytool.domain.Study;
import com.hst.studytool.domain.StudyReview;

import java.util.List;

public interface StudyQueryUseCase {

    List<Study> getAllStudies();

    Study getStudy(String id);

    List<StudyReview> getStudyReviews(String id);

}
