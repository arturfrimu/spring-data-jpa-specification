package com.arturfrimu.specification.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class GenericSpecification<T> implements Specification<T> {

    private final SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> criteriaQuery, @NonNull CriteriaBuilder criteriaBuilder) {
        List<Object> arguments = searchCriteria.arguments();
        Object arg = arguments.get(0);

        return switch (searchCriteria.searchOperation()) {
            case EQUALITY -> criteriaBuilder.equal(root.get(searchCriteria.key()), arg);
            case NEGATION -> root.get(searchCriteria.key()).in(arguments);
            case GREATER_THAN -> criteriaBuilder.greaterThan(root.get(searchCriteria.key()), (Comparable) arg);
            case LESS_THAN -> criteriaBuilder.lessThan(root.get(searchCriteria.key()), (Comparable) arg);
            case STARTS_WITH -> criteriaBuilder.like(root.get(searchCriteria.key()), arg.toString() + "%");
            case ENDS_WITH -> criteriaBuilder.like(root.get(searchCriteria.key()), "%" + arg.toString());
            case CONTAINS -> criteriaBuilder.like(root.get(searchCriteria.key()), "%" + arg.toString() + "%");
            case GREATER_THAN_OR_EQUAL_TO ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.key()), (Comparable) arg);
            case LESS_THAN_OR_EQUAL_TO ->
                    criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.key()), (Comparable) arg);
        };
    }
}
