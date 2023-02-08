package com.arturfrimu.specification.dto;

import com.arturfrimu.specification.entity.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountResponse {

    private UUID id;
    private String name;
    private Users user;

    public AccountResponse(UUID id, String name, Users user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }
}