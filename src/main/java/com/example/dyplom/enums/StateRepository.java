package com.example.dyplom.enums;



import com.example.dyplom.issues.Issue;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
public interface StateRepository extends JpaRepository<Issue, Long> {
    public List<Issue> findByState(StateName stateName);
}
