package com.cefetmg.individual.interfaces;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class IndividualFactory {
    public abstract Individual getInstance();

    public List<Individual> getInstances(int num) {
        return IntStream.range(0, num)
            .mapToObj(i -> ((Individual) getInstance()))
            .collect(Collectors.toList());
    }
}
