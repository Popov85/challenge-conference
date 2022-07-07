package com.popov.conference_challenge.repository.closure_tables;

import com.popov.conference_challenge.repository.entity.closure_tables.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
