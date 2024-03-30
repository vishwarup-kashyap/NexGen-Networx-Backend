package org.reg.resolveissueservice.repository;

import org.reg.resolveissueservice.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepo extends JpaRepository<Issue,Long> {

}
