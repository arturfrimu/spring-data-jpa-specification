package com.arturfrimu.specification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Users {

    @Id
    @Column(name = "user_id")
    private UUID id;
    @Column(name = "name")
    private String name;
}
