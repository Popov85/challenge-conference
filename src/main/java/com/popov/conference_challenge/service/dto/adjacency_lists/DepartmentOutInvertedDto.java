package com.popov.conference_challenge.service.dto.adjacency_lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentOutInvertedDto {
    private Long id;
    private Set<DepartmentOutInvertedDto> children = new HashSet<>();
    private String name;
    private Integer members;
    private Boolean archived = false;
}
