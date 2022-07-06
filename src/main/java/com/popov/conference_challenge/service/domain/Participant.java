package com.popov.conference_challenge.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    private Long id;

    @NotNull
    @Size(min = 2, max = 500)
    private String username;

    @NotNull
    @Size(min = 8, max = 100)
    private String password;

    private Boolean active = true;

    public void deactivate() {
        this.active = false;
    }
}
