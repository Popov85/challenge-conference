package com.popov.conference_challenge.service.mapper.adjacency_lists.transformer;

import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutDto;

import java.util.Map;
import java.util.Set;

public interface DepartmentTransformer {
    Map<DepartmentOutDto, Set<DepartmentOutDto>> toInverted(Set<DepartmentOutDto> dto);
}
