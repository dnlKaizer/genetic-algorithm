package com.cefetmg.individual.nQueens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class IndividualNQueensFactoryTest {

    @Test
    void constructorShouldThrowWhenNumGenesIsLowerThanFour() {
        assertThrows(IllegalArgumentException.class, () -> new IndividualNQueensFactory(3));
    }

    @Test
    void getInstanceShouldCreateIndividualWithExpectedGeneLength() {
        IndividualNQueensFactory factory = new IndividualNQueensFactory(8);

        IndividualNQueens individual = factory.getInstance();

        assertEquals(8, individual.getGenes().length);
    }
}
