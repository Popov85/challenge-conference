package com.popov.conference_challenge.web.adjacency_lists;

import com.popov.conference_challenge.service.adjacency_lists.DepartmentService;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentInDto;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutDto;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutInvertedL0Dto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(path = "/api")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(value = "/departments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentOutDto> saveDepartment(@Valid @RequestBody DepartmentInDto dto) {
        DepartmentOutDto departmentOutDto = departmentService.saveDepartment(dto);
        log.debug("Saved a department = {}", departmentOutDto);
        return ResponseEntity.ok(departmentOutDto);
    }

    @GetMapping(value = "/departments/sub-tree", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Set<DepartmentOutInvertedL0Dto> findSubTreeFromRoot() {
        log.debug("Requested all departments sub tree from root");
        return departmentService.findSubTreeFromRoot();
    }

    @GetMapping(value = "/departments/{departmentId}/sub-tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentOutInvertedL0Dto> findSubTreeById(@PathVariable Long departmentId) {
        log.debug("Requested all departments sub tree for conference with id = {}", departmentId);
        return ResponseEntity.ok(departmentService.findSubTreeById(departmentId));
    }

}
