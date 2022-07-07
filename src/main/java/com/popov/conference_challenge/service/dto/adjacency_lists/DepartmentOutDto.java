package com.popov.conference_challenge.service.dto.adjacency_lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
