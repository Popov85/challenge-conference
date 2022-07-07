package com.popov.conference_challenge.service.adjacency_lists;

import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentInDto;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutDto;
import com.popov.conference_challenge.service.mapper.adjacency_lists.transformer.DepartmentTransformer;

import java.util.Map;
import java.util.Set;

public interface DepartmentService {
    DepartmentOutDto saveDepartment(DepartmentInDto dto);
    DepartmentOutDto archiveDepartment(Long departmentId);
    Map<DepartmentOutDto, Set<DepartmentOutDto>> findSubTreeById(Long departmentId);
}
