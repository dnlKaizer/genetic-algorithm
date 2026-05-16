package com.cefetmg.problems.n_queens.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NQueensControllerTest {

    @Test
    void executeShouldThrowNullPointerExceptionInHeadlessEnvironment() {
        // Como o NQueensController usa System.console() internamente (e ele é nulo em testes maven)
        // a chamada deve resultar em um NullPointerException se passarmos por essa linha de readLine().
        assertThrows(NullPointerException.class, () -> NQueensController.execute());
    }
}
