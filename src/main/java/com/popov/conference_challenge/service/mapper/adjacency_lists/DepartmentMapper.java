package com.popov.conference_challenge.service.mapper.adjacency_lists;

import com.popov.conference_challenge.repository.entity.ajacency_lists.Department;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentInDto;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutDto;
import com.popov.conference_challenge.service.mapper.ReferenceMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ReferenceMapper.class})
public interface DepartmentMapper {
    DepartmentOutDto toDto(Department entity);
    @Mapping(target = "archived", defaultValue = "false")
    @Mapping(target = "parent", source = "parentId")
    Department toEntity(DepartmentInDto entity);
    Department toEntity(Long departmentId);
}
