package com.popov.conference_challenge.repository.entity.closure_tables;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "members")
    private Integer members;

    @Column(name = "archived")
    private Boolean archived;
}
