package com.arturfrimu.specification.service;

import com.arturfrimu.specification.dto.AccountResponse;
import com.arturfrimu.specification.entity.Account;
import com.arturfrimu.specification.entity.Users;
import com.arturfrimu.specification.repository.AccountRepository;
import com.arturfrimu.specification.repository.UserRepository;
import com.arturfrimu.specification.specification.GenericSpecificationsBuilder;
import com.arturfrimu.specification.specification.SpecificationFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public final class AccountService {
    private final SpecificationFactory<Account> accountSpecificationFactory;
    private final SpecificationFactory<Users> userSpecificationFactory;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public Page<AccountResponse> searchAccount(Pageable pageable, UUID accountId, String name) {
        GenericSpecificationsBuilder<Account> builder = new GenericSpecificationsBuilder<>();
        if (Objects.nonNull(accountId)) {
            builder.with(accountSpecificationFactory.isEqual("id", accountId));
        }
        if (StringUtils.isNotEmpty(name)) {
            builder.with(accountSpecificationFactory.contains("name", name));
        }
        return accountRepository.findAll(builder.build(), pageable)
                .map(this::apply);
    }

    private AccountResponse apply(Account account) {
        Users user = userRepository.findAll(userSpecificationFactory.isEqual("id", account.getUserId())).stream().findFirst().orElse(null);
        return new AccountResponse(account.getId(), account.getName(), user);
    }
}
