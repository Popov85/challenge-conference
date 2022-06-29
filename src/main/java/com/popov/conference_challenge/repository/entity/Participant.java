package com.popov.conference_challenge.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;
}
