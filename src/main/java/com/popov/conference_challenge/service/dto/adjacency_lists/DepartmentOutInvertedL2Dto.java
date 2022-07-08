package com.popov.conference_challenge.service.dto.adjacency_lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentOutInvertedL2Dto {
    private Long id;
    private Long parentId;
    private String name;
    private Integer members;
    private Boolean archived = false;
}