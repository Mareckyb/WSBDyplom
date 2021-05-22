package com.example.dyplom.issues;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    Issue findIssueByProjectId(Long id);
    List<Issue> findAllByProjectId(Long id);


}
