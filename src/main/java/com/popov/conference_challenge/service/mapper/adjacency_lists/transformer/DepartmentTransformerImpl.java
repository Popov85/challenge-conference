package com.popov.conference_challenge.service.mapper.adjacency_lists.transformer;

import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutDto;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutInvertedDto;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

@Component
public class DepartmentTransformerImpl implements DepartmentTransformer {
    @Override
    public Map<DepartmentOutDto, Set<DepartmentOutDto>>  toInverted(Set<DepartmentOutDto> dto) {
        Map<DepartmentOutDto, Set<DepartmentOutDto>> departmentPerParent = dto.stream()
                .filter(d->d.getParent()!=null)
                .collect(groupingBy(DepartmentOutDto::getParent, toSet()));
        return departmentPerParent;
    }
}
