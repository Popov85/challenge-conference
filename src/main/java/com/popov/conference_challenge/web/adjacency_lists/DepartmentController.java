package com.popov.conference_challenge.web.adjacency_lists;

import com.popov.conference_challenge.service.adjacency_lists.DepartmentService;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentInDto;
import com.popov.conference_challenge.service.dto.adjacency_lists.DepartmentOutDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
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
    public Map<DepartmentOutDto, Set<DepartmentOutDto>> findSubTreeFromTop() {
        log.debug("Requested all departments sub tree for conference with id = {}", "top");
        return departmentService.findSubTreeById(null);
    }

    @GetMapping(value = "/departments/{departmentId}/sub-tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<DepartmentOutDto, Set<DepartmentOutDto>> findSubTreeById(@PathVariable Long departmentId) {
        log.debug("Requested all departments sub tree for conference with id = {}", departmentId);
        return departmentService.findSubTreeById(departmentId);
    }

//    @PatchMapping(value = "/participants/{participantId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<ParticipantDto> deactivateParticipant(@PathVariable Long participantId) {
//        ParticipantDto participantDto = participantService.deactivateParticipant(participantId);
//        log.debug("Deactivated a participant = {}", participantDto);
//        return ResponseEntity.ok(participantDto);
//    }

}
