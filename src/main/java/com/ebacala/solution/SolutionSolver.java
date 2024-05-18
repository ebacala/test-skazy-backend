package com.ebacala.solution;

import org.apache.commons.collections4.iterators.PermutationIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SolutionSolver {
    @Autowired
    private SolutionRepository solutionRepository;

    /**
     * Tell if the provided answer is a solution to the riddle's equation
     * <p>
     * The riddle's equation is:
     * a + (13b / c) + d + 12e - f + (gh / i) = 87
     *
     * @param answer the provided answer
     * @return true if the provided answer is a solution, false otherwise
     */
    public Boolean isAnswerValid(Solution answer) {
        double solutionResult = (answer.getA() + ((double) (13 * answer.getB()) / answer.getC()) + answer.getD() + (12 * answer.getE()) - answer.getF() + ((double) (answer.getG() * answer.getH()) / answer.getI()));

        return solutionResult == 87;
    }

    /**
     * Generate all possible solutions to the riddle by trying every possible permutation of the allowed digits.
     * Add the solutions in the database if they do not already exist
     *
     * @return the list of all the solutions
     */
    public List<Solution> generateAllPossibleSolutions() {
        List<Integer> allowedDigits = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        PermutationIterator permutationIterator = new PermutationIterator(allowedDigits);

        List<Solution> possibleSolutions = new ArrayList<>();

        while (permutationIterator.hasNext()) {
            List<Integer> permutation = permutationIterator.next();

            Solution answer = new Solution(permutation);

            answer.setIsValid(isAnswerValid(answer));

            if (answer.getIsValid()) {
                possibleSolutions.add(answer);
            }
        }

        return possibleSolutions;
    }
}