package org.reg.raiseIssueservice.controller;

import org.reg.raiseIssueservice.dto.IssueRequest;
import org.reg.raiseIssueservice.entity.Issue;
import org.reg.raiseIssueservice.entity.User;
import org.reg.raiseIssueservice.repository.UserRepo;
import org.reg.raiseIssueservice.service.IRaiseIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RaiseIssueController {
    @Autowired
    private IRaiseIssueService raiseIssueService;




//    @PostMapping("/raiseIssue")
//    public ResponseEntity<String> raiseIssue(@RequestBody IssueRequest issueRequest) {
//        Issue issue = new Issue();
//        issue.setName(issueRequest.getIssueName());
//        issue.setIssueType(issueRequest.getIssueType());
//        issue.setRaisedAt(new Date());
//
//        // Fetch the user entity from the database based on user id
//        Optional<User> userOptional1 = Optional.ofNullable(raiseIssueService.getUserById(issueRequest.getUserId()));
//        Optional<User> userOptional2 = Optional.ofNullable(raiseIssueService.findByName(issueRequest.getUserName()));
//
//        if (userOptional1.isEmpty() ) {
//            return ResponseEntity.badRequest().build();
//        }
////        Mono<Admin> randomAdminMono = adminServiceClient.getRandomAdmin();
//
//
//
//        issue.setStatus("Active");
//        User user = userOptional1.get();
//        System.out.println(user.toString());
//        if (user.getName().equals(issueRequest.getUserName()) ) {
//            issue.setRaisedBy(user);
//
//            Issue savedIssue = raiseIssueService.raiseIssue(issue);
//            return ResponseEntity.ok("The issue is being raised.\nYour issue id is "+savedIssue.getId()+"\nPlease wait for the acceptance.");
//
//        }
//        return ResponseEntity.badRequest().build();
//
//    }

    @PostMapping("/raiseIssue")
    public ResponseEntity<String> raiseIssue(@RequestBody IssueRequest issueRequest) {
        // Fetch the user entity from the database based on user id
        Optional<User> userOptional = raiseIssueService.getUserById(issueRequest.getUserId());

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User ID does not exist.");
        }

        User user = userOptional.get();


//        if (!user.getName().equalsIgnoreCase(issueRequest.getUserName())) {
//            return ResponseEntity.badRequest().body("User ID and Username do not match.");
//        }

        Issue issue = new Issue();
        issue.setName(issueRequest.getIssueName());
        issue.setIssueType(issueRequest.getIssueType());
        issue.setRaisedAt(new Date());
        issue.setStatus("Active");
        issue.setRaisedBy(user);

        Issue savedIssue = raiseIssueService.raiseIssue(issue);
        return ResponseEntity.ok("The issue is being raised.\nYour issue id is " + savedIssue.getId() + "\nPlease wait for the acceptance.");
    }

    @PutMapping("/updateIssue")
    public Issue updateIssue(@RequestBody Issue issue)
    {
        return raiseIssueService.updateIssue(issue);
    }

    @GetMapping("/showStatus/{id}")
    public String issueStatus(@PathVariable Long id)
    {
       return  raiseIssueService.showIssueStatus(id);
    }
}
