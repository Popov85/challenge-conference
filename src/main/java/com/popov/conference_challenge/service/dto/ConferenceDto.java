package com.popov.conference_challenge.service.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ConferenceDto {
    private Long id;
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @Min(10)
    @NotNull
    private Integer seats;
    private Boolean cancelled = false;
}
