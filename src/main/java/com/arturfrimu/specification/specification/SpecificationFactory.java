package com.arturfrimu.specification.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import static com.arturfrimu.specification.specification.SearchOperation.CONTAINS;
import static com.arturfrimu.specification.specification.SearchOperation.EQUALITY;
import static java.util.Collections.singletonList;

@Component
public class SpecificationFactory<T> {

    public Specification<T> isEqual(String key, Object arg) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, EQUALITY, singletonList(arg)).build();
    }

    public Specification<T> contains(String key, Object arg) {
        GenericSpecificationsBuilder<T> builder = new GenericSpecificationsBuilder<>();
        return builder.with(key, CONTAINS, singletonList(arg)).build();
    }
}
