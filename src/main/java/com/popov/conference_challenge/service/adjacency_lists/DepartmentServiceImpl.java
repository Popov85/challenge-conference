package com.popov.conference_challenge.service.adjacency_lists;

import com.popov.conference_challenge.repository.adjacency_lists.DepartmentRepository;
import com.popov.conference_challenge.repository.entity.ajacency_lists.Department;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentInDto;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutDto;
import com.popov.conference_challenge.service.mapper.adjacency_lists.DepartmentMapper;
import com.popov.conference_challenge.service.mapper.adjacency_lists.transformer.DepartmentTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentTransformer departmentTransformer;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 DepartmentTransformer departmentTransformer,
                                 DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentTransformer = departmentTransformer;
        this.departmentMapper = departmentMapper;
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
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<DepartmentOutDto, Set<DepartmentOutDto>> findSubTreeById(Long departmentId) {
        Set<Department> result = new HashSet<>();
        // 1) departmentId = null
        // 2) departmentId <> null
        if (departmentId==null) {
            Set<Department> depth0 =
                    departmentRepository.findImmediateChildrenFromTop();
            result.addAll(depth0);
            for (Department depDepth0 : depth0) {
                Set<Department> depth1 =
                        departmentRepository.findImmediateChildrenById(depDepth0.getId());
                result.addAll(depth1);
                for (Department depDepth1 : depth1) {
                    Set<Department> depth2 =
                            departmentRepository.findImmediateChildrenById(depDepth1.getId());
                    result.addAll(depth2);
                }
            }
        }
        return departmentTransformer.toInverted(result.stream()
                .map(d->departmentMapper.toDto(d)).collect(Collectors.toSet()));
    }
}
