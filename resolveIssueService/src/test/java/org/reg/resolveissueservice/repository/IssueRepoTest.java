package org.reg.resolveissueservice.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.reg.resolveissueservice.entity.Issue;
import org.reg.resolveissueservice.repository.IssueRepo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class IssueRepoTest {

    @Mock
    private IssueRepo issueRepo;

    @Test
    public void testSaveIssue() {
        // Given
        Issue issue = new Issue(1L, "Sample Issue","issue", new Date(), "vishu", "Open");
        when(issueRepo.save(issue)).thenReturn(issue);

        // When
        Issue savedIssue = issueRepo.save(issue);

        // Then
        assertThat(savedIssue).isNotNull();
        assertThat(savedIssue.getResolvedBy()).isEqualTo("vishu");
        assertThat(savedIssue.getStatus()).isEqualTo("Open");
    }

    @Test
    public void testFindIssueById() {
        // Given
        Issue issue = new Issue(1L, "Sample Issue","issue", new Date(), "aryan", "Open");
        Long issueId = issue.getId();
        when(issueRepo.findById(issueId)).thenReturn(Optional.of(issue));

        // When
        Optional<Issue> foundIssue = issueRepo.findById(issueId);

        // Then
        assertThat(foundIssue).isPresent();
        assertThat(foundIssue.get().getResolvedBy()).isEqualTo("aryan");
    }

    @Test
    public void testFindAllIssues() {
        // Given
        when(issueRepo.findAll()).thenReturn(List.of(
                new Issue(1L, "Issue 1","iss", new Date(), "rewa", "Open"),
                new Issue(2L, "Issue 2","iss", new Date(), "sharon", "In Progress")
        ));

        // When
        List<Issue> issues = issueRepo.findAll();

        // Then
        assertThat(issues).hasSize(2);
        assertThat(issues.get(0).getResolvedBy()).isEqualTo("rewa");
        assertThat(issues.get(1).getResolvedBy()).isEqualTo("sharon");
    }

    @Test
    public void testUpdateIssue() {
        // Given
        Issue issue = new Issue(1L, "Sample Issue","iss", new Date(), "vishu", "Open");
        when(issueRepo.save(issue)).thenReturn(issue);

        // When
        Issue updatedIssue = issueRepo.save(issue);

        // Then
        assertThat(updatedIssue.getStatus()).isEqualTo("Open");
    }

    @Test
    public void testDeleteIssue() {
        // Given
        Issue issue = new Issue(1L, "Sample Issue","iss", new Date(), "aryan", "Open");
        Long issueId = issue.getId();

        // When
        issueRepo.deleteById(issueId);

        // Then
        assertThat(issueRepo.findById(issueId)).isEmpty();
    }
}
