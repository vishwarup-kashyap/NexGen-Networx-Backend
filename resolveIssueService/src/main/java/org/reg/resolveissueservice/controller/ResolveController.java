package org.reg.resolveissueservice.controller;


import org.apache.catalina.User;
import org.reg.resolveissueservice.dto.AcceptIssueDto;
import org.reg.resolveissueservice.dto.IssueResolve;
import org.reg.resolveissueservice.entity.Admin;
import org.reg.resolveissueservice.entity.Issue;
import org.reg.resolveissueservice.repository.IAdminRepo;
import org.reg.resolveissueservice.repository.IssueRepo;
import org.reg.resolveissueservice.service.IResolveIssueService;
import org.reg.resolveissueservice.service.ResolveissueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ResolveController {

    @Autowired
    private IResolveIssueService resolveissueService;



    @GetMapping("/acceptIssue")
    public ResponseEntity<AcceptIssueDto> acceptIssue(@RequestBody IssueResolve issueResolve) {
        Issue issue = resolveissueService.getIssueById(issueResolve.getIssueId());

        Admin existingAdmin=resolveissueService.getAdminByid(issueResolve.getAdminId());

        if(existingAdmin==null||issue==null)
        {
            return ResponseEntity.badRequest().build();
        }

//        issue.setResolvedBy(existingAdmin);

        Issue existingIssue=resolveissueService.acceptissue(issue);
        AcceptIssueDto acceptIssueDto=new AcceptIssueDto();
        acceptIssueDto.setId(existingIssue.getId());
        acceptIssueDto.setName(existingIssue.getName());
        acceptIssueDto.setStatus(existingIssue.getStatus());


        return ResponseEntity.ok(acceptIssueDto);

    }

    @GetMapping("/resolveIssue")
    public ResponseEntity<Issue> resolveIssue(@RequestBody IssueResolve issueResolve)
    {
        Issue issue = resolveissueService.getIssueById(issueResolve.getIssueId());

        Admin existingAdmin=resolveissueService.getAdminByid(issueResolve.getAdminId());

        if(existingAdmin==null||issue==null)
        {
            return ResponseEntity.badRequest().build();
        }

        issue.setResolvedAt(new Date());
        issue.setResolvedBy(existingAdmin.getName());

//        issue.setResolvedBy(existingAdmin);

        Issue existingIssue=resolveissueService.resolveissue(issue);
        return ResponseEntity.ok(existingIssue);
    }


    @GetMapping("/issueStatus/{id}")
    public String getIssueStatus(@PathVariable Long id) {
        Issue issue = resolveissueService.getIssueById(id);

        return "The status of your issue "+issue.getName()+" is "+issue.getStatus()+" now.";
    }

    @GetMapping("/getAllIssues")
    public List<Issue> getAllIssues()
    {
        return resolveissueService.getAllIssue();
    }
    @GetMapping("/getIssue/{id}")
    public Issue getIssueById(@PathVariable Long id)
    {
        return resolveissueService.getIssueById(id);
    }
}
