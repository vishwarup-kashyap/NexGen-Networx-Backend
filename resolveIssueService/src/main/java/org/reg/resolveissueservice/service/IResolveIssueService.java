package org.reg.resolveissueservice.service;


import org.reg.resolveissueservice.entity.Admin;
import org.reg.resolveissueservice.entity.Issue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IResolveIssueService {

    public List<Issue> getAllIssue();
    public Issue getIssueById(Long id);
    public Issue acceptissue(Issue issue);
    public Issue resolveissue(Issue issue);
    public  Issue getIssueStatus(Long id);

    public Admin getAdminByid(Long id);
}
