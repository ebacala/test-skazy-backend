package com.ebacala.service;

import com.ebacala.solution.Solution;
import com.ebacala.solution.SolutionSolver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SolutionSolverTest {
    @Autowired
    private SolutionSolver solutionSolver;

    @Test
    void isAnswerValid_withInvalidAnswer() {
        Solution invalidSolution = new Solution(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        assertFalse(solutionSolver.isAnswerValid(invalidSolution));
    }

    @Test
    void isAnswerValid_withValidAnswer1() {
        Solution validSolution = new Solution(Arrays.asList(6, 9, 3, 5, 2, 1, 7, 8, 4));

        assertTrue(solutionSolver.isAnswerValid(validSolution));
    }

    @Test
    void isAnswerValid_withValidAnswer2() {
        Solution validSolution = new Solution(Arrays.asList(7, 2, 8, 9, 6, 5, 1, 3, 4));

        assertTrue(solutionSolver.isAnswerValid(validSolution));
    }

    @Test
    void generateAllPossibleSolutions_returns128Solutions() {
        assertEquals(128, solutionSolver.generateAllPossibleSolutions().size());
    }
}