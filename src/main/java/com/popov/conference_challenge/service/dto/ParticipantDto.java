package com.popov.conference_challenge.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ParticipantDto {
    private Long id;

    @NotNull
    @Size(min = 2, max = 500)
    private String username;

    @NotNull
    @Size(min = 8, max = 100)
    private String password;

    private Boolean active = true;
}
