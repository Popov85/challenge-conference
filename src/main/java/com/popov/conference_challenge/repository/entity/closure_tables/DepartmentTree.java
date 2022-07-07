package com.popov.conference_challenge.repository.entity.closure_tables;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="department_tree")
public class DepartmentTree {

    @EmbeddedId
    private DepartmentTreeId departmentTreeId = new DepartmentTreeId();

    @MapsId("departmentId")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @MapsId("childId")
    @ManyToOne
    @JoinColumn(name = "child_id")
    private Department child;
}
