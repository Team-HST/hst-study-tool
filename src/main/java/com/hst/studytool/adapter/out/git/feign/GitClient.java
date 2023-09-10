package com.hst.studytool.adapter.out.git.feign;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "gitClient", url = "${git.api.endpoint}", decode404 = true)
@Headers({
    "Authorization: ${git.api.token}",
    "Accept: application/vnd.github.v3+json"
})
public interface GitClient {

    @GetMapping("/contents/study")
    List<GitItem> getStudies();

    @GetMapping("/contents/study/{id}/README.md")
    GitItem getStudy(@PathVariable String id);

    @GetMapping("/contents/study/{id}/reviews")
    List<GitItem> getStudyReviews(@PathVariable String id);

    @GetMapping("/contents/study/{id}/reviews/{name}")
    GitItem getStudyReview(@PathVariable String id, @PathVariable String name);

}
