package org.reg.raiseIssueservice.service;

import org.reg.raiseIssueservice.entity.Issue;
import org.reg.raiseIssueservice.entity.User;

import java.util.Optional;

public interface IRaiseIssueService {

    public Issue raiseIssue(Issue issue);
    public Issue updateIssue(Issue issue);

    public String showIssueStatus(Long id);

    public Optional<User> getUserById(Long id);

    User findByName(String name);
}
