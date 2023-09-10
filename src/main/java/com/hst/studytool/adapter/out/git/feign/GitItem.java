package com.hst.studytool.adapter.out.git.feign;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Base64;

public record GitItem(
    @JsonProperty("git_url") String gitUrl,
    @JsonProperty("html_url") String htmlUrl,
    @JsonProperty("name") String name,
    @JsonProperty("path") String path,
    @JsonProperty("type") String type,
    @JsonProperty("content") String content
) {

    @JsonProperty("rawContent")
    public String getContentRawString() {
        if (this.content == null || this.content.isBlank()) {
            return "";
        }
        return new String(Base64.getDecoder().decode(content.replace("\n", "")));
    }


}
