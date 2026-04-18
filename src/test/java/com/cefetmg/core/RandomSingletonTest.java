package com.cefetmg.core;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Random;

import org.junit.jupiter.api.Test;

class RandomSingletonTest {

    @Test
    void getInstanceShouldReturnSameReferenceWhenSeedIsUnchanged() {
        Random first = RandomSingleton.getInstance();
        Random second = RandomSingleton.getInstance();

        assertSame(first, second);
    }
}
