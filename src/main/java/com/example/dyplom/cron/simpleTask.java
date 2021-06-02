package com.example.dyplom.cron;

import com.example.dyplom.enums.EnumService;
import com.example.dyplom.issues.IssueRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class simpleTask {
    final IssueRepository issueRepository;
    final EnumService enumService;

    public simpleTask(IssueRepository issueRepository, EnumService enumService) {
        this.issueRepository = issueRepository;
        this.enumService = enumService;
    }

    @Scheduled (fixedRate = 1000)
    public void executeTask(){


        //Long countOfIssues = issueRepository.count();
        //System.out.println("Ilość zadań: "+ countOfIssues.toString());

        //if (enumService.chceckStateNameExists("TODO") == true)
        //System.out.println("Status TODO istnieje");
        //else //System.out.println("Status TODO nie istnieje");

        //System.out.println("Ilość statusów zdarzeń: " + enumService.getStateNameSize());
        //System.out.println("Ilość typów zdarzeń: "+ enumService.getTypeOfIssueSize());

    }
}
