package com.example.dbdemo.utilities;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterUtils<T> {
    public List<T> filterList(List<T> list, Predicate<T> condition) {
        return list
                .stream()
                .filter(condition)
                .collect(Collectors.toList());
    }
}
