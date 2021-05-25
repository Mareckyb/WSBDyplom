package com.example.dyplom.issues;

import com.example.dyplom.enums.StateName;
import com.example.dyplom.enums.TypeOfIssue;
import com.example.dyplom.person.Person;
import com.example.dyplom.projects.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
@NoArgsConstructor
public class IssueFilter {

    StateName state;
    Project project;
    Person assignee;
    String title;
    TypeOfIssue typeOfIssue;

    String globalSearch;

    private Specification<Issue> hasState(){
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("state"), state);
    }

    private Specification<Issue> hasProject(){
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("project"), project);
    }

    private Specification<Issue> hasAssignee() {
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("assignee"), assignee);
    }

    private Specification<Issue> hasTitle() {
        return (issueRoot, query, builder) -> builder.like(builder.lower(issueRoot.get("title")), "%" + title.toLowerCase() + "%");
    }

    private Specification<Issue> hasTypeOfIssue() {
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("typeOfIssue"), typeOfIssue);
    }

    private Specification<Issue> globalSearching() {

        Specification<Issue> hasTitle = (issueRoot, query, builder) -> builder.like(builder.lower(issueRoot.get("title")), "%" + globalSearch.toLowerCase() + "%");
        Specification<Issue> hasDescription = (issueRoot, query, builder) -> builder.like(builder.lower(issueRoot.get("description")), "%" + globalSearch.toLowerCase() + "%");

        return hasTitle.or(hasDescription);
    }

    public Specification<Issue> buildQuery() {
        Specification<Issue> spec = Specification.where(null);

        if (project != null) {
            spec = spec.and(hasProject());
        }

        if (assignee != null) {
            spec = spec.and(hasAssignee());
        }

        if (state != null) {
            spec = spec.and(hasState());
        }

        if (typeOfIssue != null) {
            spec = spec.and(hasTypeOfIssue());
        }

        if (title != null) {
            spec = spec.and(hasTitle());
        }

        if (globalSearch != null) {
            spec = spec.and(globalSearching());
        }

        return spec;
    }



}
