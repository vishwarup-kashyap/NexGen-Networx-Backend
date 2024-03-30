package org.reg.resolveissueservice.service;

import org.reg.resolveissueservice.dto.IssueResolve;
import org.reg.resolveissueservice.entity.Admin;
import org.reg.resolveissueservice.entity.Issue;
import org.reg.resolveissueservice.repository.IAdminRepo;
import org.reg.resolveissueservice.repository.IssueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResolveissueService implements IResolveIssueService{

    @Autowired
    private IssueRepo issueRepo;
    @Autowired
    private IAdminRepo adminRepo;
    @Override
    public Issue acceptissue(Issue issue) {
        issue.setStatus("In Process");
       return  issueRepo.save(issue);
    }

    @Override
    public Issue resolveissue(Issue issue) {
        issue.setStatus("Resolved");
        return  issueRepo.save(issue);
    }

    @Override
    public Issue getIssueStatus(Long id) {
        return issueRepo.findById(id).orElse(null);
    }

    @Override
    public Admin getAdminByid(Long id) {
        return adminRepo.findById(id).orElse(null);
    }

    @Override
    public List<Issue> getAllIssue() {
        return issueRepo.findAll();
    }

    @Override
    public Issue getIssueById(Long id) {
        return issueRepo.findById(id).orElse(null);
    }



}
