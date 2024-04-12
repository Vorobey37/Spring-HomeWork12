package org.example.issue_service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    private List<Issue> issues;
    private final BookProvider bookProvider;
    private final ReaderProvider readerProvider;

    @PostConstruct
    public void createIssues(){
        issues = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Issue issue = new Issue();
            issue.setId(UUID.randomUUID());
            issue.setBook(bookProvider.getRandomBook());
            issue.setReader(readerProvider.getRandomReader());

            issues.add(issue);
        }
    }

    @GetMapping("refresh")
    public List<Issue> refresh(){
        createIssues();
        return issues;
    }

    @GetMapping()
    public List<Issue> getAllIssues(){
        return issues;
    }

    @GetMapping("random")
    public Issue getRandomIssue(){
        Random random = new Random();
        return issues.get(random.nextInt(issues.size()));
    }
}
