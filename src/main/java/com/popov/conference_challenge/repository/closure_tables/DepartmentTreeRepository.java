package com.popov.conference_challenge.repository.closure_tables;

import com.popov.conference_challenge.repository.entity.closure_tables.DepartmentTree;
import com.popov.conference_challenge.repository.entity.closure_tables.DepartmentTreeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentTreeRepository extends JpaRepository<DepartmentTree, DepartmentTreeId> {

}
