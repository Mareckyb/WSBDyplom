package com.example.dyplom.enums;

import com.example.dyplom.issues.Issue;
import org.springframework.data.repository.CrudRepository;


import java.util.List;


public interface TypeOfIssueRepository extends CrudRepository<Issue, Long> {
    public List<Issue> findByTypeOfIssue(TypeOfIssue typeOfIssue);
}
