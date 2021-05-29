package com.example.dyplom.cron;

import com.example.dyplom.issues.IssueRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class simpleTask {
    final IssueRepository issueRepository;

    public simpleTask(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Scheduled (fixedRate = 1000)
    public void executeTask(){

        Long countOfIssues = issueRepository.count();
        System.out.println("Ilość zadań: "+ countOfIssues.toString());
    }
}
