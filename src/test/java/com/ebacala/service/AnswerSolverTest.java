package com.ebacala.service;

import com.ebacala.answer.Answer;
import com.ebacala.answer.AnswerSolver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnswerSolverTest {
    @Autowired
    private AnswerSolver answerSolver;

    @Test
    void isAnswerValid_withInvalidAnswer() {
        Answer invalidAnswer = new Answer(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        assertFalse(answerSolver.isAnswerValid(invalidAnswer));
    }

    @Test
    void isAnswerValid_withValidAnswer1() {
        Answer validAnswer = new Answer(Arrays.asList(6, 9, 3, 5, 2, 1, 7, 8, 4));

        assertTrue(answerSolver.isAnswerValid(validAnswer));
    }

    @Test
    void isAnswerValid_withValidAnswer2() {
        Answer validAnswer = new Answer(Arrays.asList(7, 2, 8, 9, 6, 5, 1, 3, 4));

        assertTrue(answerSolver.isAnswerValid(validAnswer));
    }

    @Test
    void generateAllPossibleSolutions_returns128Solutions() {
        assertEquals(128, answerSolver.generateAllPossibleSolutions().size());
    }
}