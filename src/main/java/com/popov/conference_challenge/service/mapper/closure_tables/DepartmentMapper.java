package com.popov.conference_challenge.service.mapper.closure_tables;

import com.popov.conference_challenge.repository.entity.closure_tables.Department;
import com.popov.conference_challenge.service.dto.closure_tables.DepartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {
    DepartmentDto toDto(Department entity);

    @Mapping(target = "archived", defaultValue = "false")
    Department toEntity(DepartmentDto entity);
}
