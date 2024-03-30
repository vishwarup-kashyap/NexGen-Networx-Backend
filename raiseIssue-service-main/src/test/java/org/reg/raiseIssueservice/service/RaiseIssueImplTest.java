package org.reg.raiseIssueservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.reg.raiseIssueservice.entity.Issue;
import org.reg.raiseIssueservice.entity.User;
import org.reg.raiseIssueservice.repository.IssueRepo;
import org.reg.raiseIssueservice.repository.UserRepo;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RaiseIssueImplTest {

    @Mock
    private IssueRepo issueRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private RaiseIssueImpl raiseIssueService;

    @BeforeEach
    void setUp() {
        lenient().when(webClient.get()).thenReturn(requestHeadersUriSpec);
        lenient().when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        lenient().when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        lenient().when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just("Issue Status"));
    }

    @Test
    void raiseIssueTest() {
        Issue issue = new Issue();
        when(issueRepo.save(any(Issue.class))).thenReturn(issue);

        Issue savedIssue = raiseIssueService.raiseIssue(issue);

        verify(issueRepo, times(1)).save(issue);
    }

    @Test
    void updateIssueTest() {
        Issue issue = new Issue();
        issue.setId(1L);
        when(issueRepo.findById(anyLong())).thenReturn(Optional.of(issue));
        when(issueRepo.save(any(Issue.class))).thenReturn(issue);

        Issue updatedIssue = raiseIssueService.updateIssue(issue);

        verify(issueRepo, times(1)).save(issue);
    }

    @Test
    void showIssueStatusTest() {
        // Make sure to adapt this test if you need to call the WebClient stubbings
        String status = raiseIssueService.showIssueStatus(1L);
        assertEquals("Issue Status", status);
    }

    @Test
    void getUserByIdTest() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> fetchedUser = raiseIssueService.getUserById(userId);

        assertEquals(userId, fetchedUser.get());
    }
}
