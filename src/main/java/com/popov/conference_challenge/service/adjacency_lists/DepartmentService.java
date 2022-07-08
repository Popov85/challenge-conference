package com.popov.conference_challenge.service.adjacency_lists;

import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentInDto;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutDto;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutInvertedL0Dto;

import java.util.Set;

public interface DepartmentService {
    DepartmentOutDto saveDepartment(DepartmentInDto dto);
    DepartmentOutDto archiveDepartment(Long departmentId);
    Set<DepartmentOutInvertedL0Dto> findSubTreeFromRoot();
    DepartmentOutInvertedL0Dto findSubTreeById(Long departmentId);
}
