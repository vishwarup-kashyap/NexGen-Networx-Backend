package org.reg.raiseIssueservice.service;

import org.reg.raiseIssueservice.entity.Issue;
import org.reg.raiseIssueservice.entity.User;
import org.reg.raiseIssueservice.repository.IssueRepo;
import org.reg.raiseIssueservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class RaiseIssueImpl implements IRaiseIssueService{
    @Autowired
    private IssueRepo issueRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WebClient webClient;


    @Override
    public Issue raiseIssue(Issue issue) {
        return issueRepo.save(issue);
    }

    @Override
    public Issue updateIssue(Issue issue) {


        Optional<Issue> existingIssueOptional = issueRepo.findById(issue.getId());
        if(existingIssueOptional.isPresent())
        {
            Issue existingIssue = existingIssueOptional.get();
            existingIssue.setStatus(issue.getStatus());

            issueRepo.save(existingIssue);
            return existingIssue;
        }
        else
        {
            return null;
        }
    }

    public String showIssueStatus(Long id)
    {

       return webClient.get().uri("http://localhost:9090/resolveIssue/api/issueStatus/"+id).retrieve().bodyToMono(String.class).block();


    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public User findByName(String name)
    {
        return userRepo.findByName(name);
    }


}
