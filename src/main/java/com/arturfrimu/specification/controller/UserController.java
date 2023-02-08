package com.arturfrimu.specification.controller;

import com.arturfrimu.specification.entity.Users;
import com.arturfrimu.specification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public final class UserController {
    private final UserService userService;

    @GetMapping
    public Page<Users> searchUsers(@RequestParam(value = "userId", required = false, defaultValue = "") UUID userId,
                                   @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                   @PageableDefault(size = 20) Pageable pageable) {
        return userService.searchUsers(pageable, userId, name);
    }
}
