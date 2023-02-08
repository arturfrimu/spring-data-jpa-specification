package com.arturfrimu.specification.specification;

import java.util.List;

public record SearchCriteria(String key, SearchOperation searchOperation, boolean isOrOperation, List<Object> arguments) { }
