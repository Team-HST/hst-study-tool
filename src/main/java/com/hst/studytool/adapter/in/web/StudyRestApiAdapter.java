package com.hst.studytool.adapter.in.web;

import com.hst.studytool.domain.Study;
import com.hst.studytool.domain.StudyReview;
import com.hst.studytool.domain.port.in.StudyCommandUseCase;
import com.hst.studytool.domain.port.in.StudyQueryUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("studies")
public class StudyRestApiAdapter {

    private final StudyQueryUseCase studyQueryUseCase;
    private final StudyCommandUseCase studyCommandUseCase;

    public StudyRestApiAdapter(StudyQueryUseCase studyQueryUseCase, StudyCommandUseCase studyCommandUseCase) {
        this.studyQueryUseCase = studyQueryUseCase;
        this.studyCommandUseCase = studyCommandUseCase;
    }

    @GetMapping
    public List<Study> getStudies() {
        return studyQueryUseCase.getAllStudies();
    }

    @GetMapping("/{id}")
    public Study getStudy(@PathVariable String id) {
        return studyQueryUseCase.getStudy(id);
    }

    @GetMapping("/{id}/reviews")
    public List<StudyReview> getStudyReviews(@PathVariable String id) {
        return studyQueryUseCase.getStudyReviews(id);
    }

    @PutMapping("/{id}")
    public void updateStudy(@PathVariable String id, @RequestBody StudyUpdateRequest request) {
        studyCommandUseCase.updateStudy(request.toCommand(id));
    }

}
