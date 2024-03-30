package org.reg.raiseIssueservice.repository;

import org.reg.raiseIssueservice.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepo extends JpaRepository<Issue, Long> {
}
