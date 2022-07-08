package com.popov.conference_challenge.service.adjacency_lists;

import com.popov.conference_challenge.repository.adjacency_lists.DepartmentRepository;
import com.popov.conference_challenge.repository.entity.ajacency_lists.Department;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentInDto;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutDto;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutInvertedL0Dto;
import com.popov.conference_challenge.service.mapper.adjacency_lists.DepartmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper,
                                 DepartmentRepository departmentRepository) {
        this.departmentMapper = departmentMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public DepartmentOutDto saveDepartment(DepartmentInDto dto) {
        Department department = departmentMapper.toEntity(dto);
        Department result = departmentRepository.save(department);
        return departmentMapper.toDto(result);
    }

    @Override
    public DepartmentOutDto archiveDepartment(Long departmentId) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    @Transactional(readOnly = true)
    public Set<DepartmentOutInvertedL0Dto> findSubTreeFromRoot() {
        Set<Department> depWithChildren = departmentRepository.findAllWithChildrenFromRoot();
        log.debug("Entity Sub-tree from root = {}",depWithChildren);
        Set<DepartmentOutInvertedL0Dto> departmentOutInvertedDto =
                departmentMapper.toInvertedDto(depWithChildren);
        log.debug("Dto Sub-tree from root = {}", departmentOutInvertedDto);
        return departmentOutInvertedDto;
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentOutInvertedL0Dto findSubTreeById(Long departmentId) {
        Department depWithChildren = departmentRepository.findOneWithChildrenById(departmentId);
        DepartmentOutInvertedL0Dto departmentOutInvertedDto =
                departmentMapper.toInvertedDto(depWithChildren);
        log.debug("Sub-tree from depId = {} = {}", departmentId, departmentOutInvertedDto);
        return departmentOutInvertedDto;
    }
}
