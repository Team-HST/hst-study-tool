package com.hst.studytool.domain.port.out;

import com.hst.studytool.domain.Study;
import com.hst.studytool.domain.StudyReview;

import java.util.List;
import java.util.Optional;

public interface StudyReadPort {

    List<Study> findAll();

    Optional<Study> findOne(String id);

    List<StudyReview> findStudyReviews(String id);

}
