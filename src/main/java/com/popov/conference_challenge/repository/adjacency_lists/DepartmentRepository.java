package com.popov.conference_challenge.repository.adjacency_lists;

import com.popov.conference_challenge.repository.entity.ajacency_lists.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = "SELECT d FROM Department d where d.parent.id is NULL")
    Set<Department> findImmediateChildrenFromTop();

    @Query(value = "SELECT d FROM Department d where d.parent.id =?1")
    Set<Department> findImmediateChildrenById(Long departmentId);
}
