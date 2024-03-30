package org.reg.raiseIssueservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.reg.raiseIssueservice.dto.IssueRequest;
import org.reg.raiseIssueservice.entity.Issue;
import org.reg.raiseIssueservice.entity.User;
import org.reg.raiseIssueservice.service.IRaiseIssueService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RaiseIssueController.class)
public class RaiseIssueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IRaiseIssueService raiseIssueService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void raiseIssueTest() throws Exception {
        IssueRequest issueRequest = new IssueRequest();
        issueRequest.setIssueName("Test Issue");

        User user = new User();
        user.setId(1L);

        Issue issue = new Issue();
        issue.setId(1L);
        issue.setName("Test Issue");
        issue.setRaisedBy(user);
        issue.setStatus("Active");

        when(raiseIssueService.getUserById(any(Long.class))).thenReturn(Optional.of(user));
        when(raiseIssueService.raiseIssue(any(Issue.class))).thenReturn(issue);

        mockMvc.perform(post("/api/raiseIssue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(issueRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("The issue is being raised.\nYour issue id is 1\nPlease wait for the acceptance."));
    }

    @Test
    public void updateIssueTest() throws Exception {
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setName("Updated Issue");
        issue.setStatus("Resolved");

        when(raiseIssueService.updateIssue(any(Issue.class))).thenReturn(issue);

        mockMvc.perform(put("/api/updateIssue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(issue)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(issue.getId()))
                .andExpect(jsonPath("$.name").value(issue.getName()))
                .andExpect(jsonPath("$.status").value(issue.getStatus()));
    }

    @Test
    public void issueStatusTest() throws Exception {
        Long issueId = 1L;
        String issueStatus = "Active";

        when(raiseIssueService.showIssueStatus(issueId)).thenReturn(issueStatus);

        mockMvc.perform(get("/api/showStatus/{id}", issueId))
                .andExpect(status().isOk())
                .andExpect(content().string(issueStatus));
    }
}
