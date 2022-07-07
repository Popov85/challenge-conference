package com.popov.conference_challenge.service.closure_tables;

import com.popov.conference_challenge.service.dto.closure_tables.DepartmentDto;

import java.util.Set;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto dto);
    DepartmentDto archiveDepartment(DepartmentDto dto);
    Set<DepartmentDto> getChildDepartments(Long departmentId);
}
