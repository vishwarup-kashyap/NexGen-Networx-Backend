package org.reg.registrationservice.repository;

import org.reg.registrationservice.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Long> {
}
