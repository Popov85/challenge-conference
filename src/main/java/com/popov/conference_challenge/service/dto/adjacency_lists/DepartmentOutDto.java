package com.popov.conference_challenge.service.dto.adjacency_lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentOutDto {
    private Long id;
    private DepartmentOutDto parent;
    private String name;
    private Integer members;
    private Boolean archived = false;
}
