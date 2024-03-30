package org.reg.raiseIssueservice.repository;

import org.reg.raiseIssueservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    User findByName(String name);
}
