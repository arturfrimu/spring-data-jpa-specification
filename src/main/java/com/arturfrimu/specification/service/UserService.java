package com.arturfrimu.specification.service;

import com.arturfrimu.specification.entity.Users;
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
public final class UserService {
    private final SpecificationFactory<Users> userSpecificationFactory;
    private final UserRepository userRepository;

    public Page<Users> searchUsers(Pageable pageable, UUID userId, String name) {
        GenericSpecificationsBuilder<Users> builder = new GenericSpecificationsBuilder<>();
        if (Objects.nonNull(userId)) {
            builder.with(userSpecificationFactory.isEqual("id", userId));
        }
        if (StringUtils.isNotEmpty(name)) {
            builder.with(userSpecificationFactory.isEqual("name", name));
        }
        return userRepository.findAll(builder.build(), pageable);
    }
}
