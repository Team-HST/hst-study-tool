package com.hst.studytool.adapter.out.git.convertor;

import com.hst.studytool.domain.Study;
import com.hst.studytool.domain.StudyReview;
import com.hst.studytool.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hst.studytool.utils.StringTemplateUtils.template;

@Service
public class GitContentDomainConvertor {

    private static final Logger log = LoggerFactory.getLogger(GitContentDomainConvertor.class);
    private static final Pattern SOURCE_DATA_HEADER_PATTERN = Pattern.compile("<!-- (.+) -->");
    private static final String SOURCE_DATA_HEADER = "<!-- ${data} -->\n";

    private static final String STUDY_TEMPLATE = SOURCE_DATA_HEADER +
    """
    # ${bookName}
    
    ## 책 소개
    ${bookDescription}
    
    ## 목차            
    ${tableOfContents}
    """;

    private static final String STUDY_REVIEW_TEMPLATE = SOURCE_DATA_HEADER +
    """
    ## 스터디 정보
    - 일자 : ${studyDate}
    - 범위 : ${startPage}p ~ ${endPage}p
    
    ## 참가자 목록
    - ${participants}

    ## 리뷰          
    ${comment}
    """;

    public String convertDomainToGitContent(Study study) {
        return template(STUDY_TEMPLATE, Map.of(
        "bookName", study.bookName(),
        "bookDescription", study.bookDescription(),
        "tableOfContents", study.tableOfContents(),
        "data", JsonUtils.toJson(study)
        ));
    }

    public String convertDomainToGitContent(StudyReview studyReview) {
        return template(STUDY_REVIEW_TEMPLATE, Map.of(
                "studyDate", studyReview.studyDate(),
                "startPage", studyReview.startPage(),
                "endPage", studyReview.endPage(),
                "participants", studyReview.participants(),
                "comment", studyReview.comment(),
                "data", JsonUtils.toJson(studyReview)
        ));
    }

    public <T> T convertGitContentToDomain(String gitContent, Class<T> type) {
        String[] lines = gitContent.split("\n");
        if (lines.length < 1) {
            log.warn("Invalid git content format. {}", gitContent);
            return null;
        }

        String headerLine = lines[0];
        Matcher matcher = SOURCE_DATA_HEADER_PATTERN.matcher(headerLine);
        if (!matcher.matches()) {
            log.warn("Invalid git content format. {}", gitContent);
            return null;
        }

        return JsonUtils.fromJson(matcher.group(1), type);
    }

    public static void main(String[] args) {
        GitContentDomainConvertor convertor = new GitContentDomainConvertor();
        String s = convertor.convertDomainToGitContent(new StudyReview(List.of("a", "b"), LocalDate.now(), 10, 20, "자유형식 내용"));
        System.out.println(s);


        String d = "<!-- {\"participants\":[\"a\",\"b\"],\"studyDate\":\"2023-09-10\",\"startPage\":10,\"endPage\":20,\"comment\":\"자유형식 내용\"} -->\n" +
                "## 스터디 정보\n" +
                "- 일자 : 2023-09-10\n" +
                "- 범위 : 10p ~ 20p\n" +
                "\n" +
                "## 참가자 목록\n" +
                "- [a, b]\n" +
                "\n" +
                "## 리뷰\n" +
                "자유형식 내용";
        System.out.println(convertor.convertGitContentToDomain(d, StudyReview.class));
    }

}
