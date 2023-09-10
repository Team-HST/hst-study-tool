package com.hst.studytool.adapter.out.git;

import com.hst.studytool.adapter.out.git.convertor.GitContentDomainConvertor;
import com.hst.studytool.adapter.out.git.feign.GitClient;
import com.hst.studytool.adapter.out.git.feign.GitItem;
import com.hst.studytool.domain.Study;
import com.hst.studytool.domain.StudyReview;
import com.hst.studytool.domain.port.out.StudyReadPort;
import com.hst.studytool.domain.port.out.StudyWritePort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudyGitAdapter implements StudyReadPort, StudyWritePort {

    private static final String DIR_TYPE = "dir";

    private final GitClient gitClient;
    private final GitContentDomainConvertor gitContentDomainConvertor;

    public StudyGitAdapter(GitClient gitClient, GitContentDomainConvertor gitContentDomainConvertor) {
        this.gitClient = gitClient;
        this.gitContentDomainConvertor = gitContentDomainConvertor;
    }

    @Override
    public List<Study> findAll() {
        return gitClient.getStudies().stream()
                .filter(item -> item.type().equals(DIR_TYPE))
                .map(item -> gitClient.getStudy(item.name()))
                .map(item -> gitContentDomainConvertor.convertGitContentToDomain(item.getContentRawString(), Study.class))
                .toList();
    }

    @Override
    public Optional<Study> findOne(String id) {
        GitItem gitItem = gitClient.getStudy(id);
        Study study = gitContentDomainConvertor.convertGitContentToDomain(gitItem.getContentRawString(), Study.class);
        return Optional.ofNullable(study);
    }

    @Override
    public List<StudyReview> findStudyReviews(String id) {
        return gitClient.getStudyReviews(id).stream()
                .map(item -> gitClient.getStudyReview(id, item.name()))
                .map(item -> gitContentDomainConvertor.convertGitContentToDomain(item.getContentRawString(), StudyReview.class))
                .toList();
    }

    @Override
    public void save(Study study) {

    }

}
