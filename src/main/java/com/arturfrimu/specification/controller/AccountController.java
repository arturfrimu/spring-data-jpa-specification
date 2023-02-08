package com.arturfrimu.specification.controller;

import com.arturfrimu.specification.dto.AccountResponse;
import com.arturfrimu.specification.service.AccountService;
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
@RequestMapping("/accounts")
public final class AccountController {
    private final AccountService accountService;

    @GetMapping
    public Page<AccountResponse> searchAccounts(@RequestParam(value = "accountId", required = false, defaultValue = "") UUID accountId,
                                                @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                @PageableDefault(size = 20) Pageable pageable) {
        return accountService.searchAccount(pageable, accountId, name);
    }
}
