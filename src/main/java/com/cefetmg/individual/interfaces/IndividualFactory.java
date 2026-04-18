package com.cefetmg.individual.interfaces;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class IndividualFactory<T> {
    public abstract Individual<T> getInstance();

    public List<Individual<T>> getInstances(int num) {
        return IntStream.range(0, num)
            .mapToObj(i -> ((Individual<T>) getInstance()))
            .collect(Collectors.toList());
    }
}
