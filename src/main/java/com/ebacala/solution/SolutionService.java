package com.ebacala.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {
    @Autowired
    private SolutionSolver solutionSolver;

    @Autowired
    private SolutionRepository solutionRepository;

    public List<Solution> getAllSolutions() {
        return solutionRepository.findAll();
    }

    public List<Solution> getFilteredSolutions(Example<Solution> filteredSolution) {
        return solutionRepository.findAll(filteredSolution);
    }

    public Optional<Solution> getSolutionById(Long id) {
        return solutionRepository.findById(id);
    }

    public Solution createSolution(Solution solution) {
        solution.setIsValid(solutionSolver.isAnswerValid(solution));

        return solutionRepository.saveAndFlush(solution);
    }

    public Solution updateSolution(Long id, Solution solution) {
        Solution foundSolution = solutionRepository.findById(id).orElseGet(Solution::new);

        foundSolution.setId(id);
        foundSolution.setA(solution.getA());
        foundSolution.setB(solution.getB());
        foundSolution.setC(solution.getC());
        foundSolution.setD(solution.getD());
        foundSolution.setE(solution.getE());
        foundSolution.setF(solution.getF());
        foundSolution.setG(solution.getG());
        foundSolution.setH(solution.getH());
        foundSolution.setI(solution.getI());
        foundSolution.setIsValid(solutionSolver.isAnswerValid(foundSolution));

        return solutionRepository.saveAndFlush(foundSolution);
    }

    public void deleteSolutionById(Long id) {
        solutionRepository.deleteById(id);
    }

    public void deleteAllSolutions() {
        solutionRepository.deleteAllInBatch();
    }

    public Long generateAllPossibleSolutions() {
        long start = System.currentTimeMillis();

        List<Solution> allPossibleSolutions = solutionSolver.generateAllPossibleSolutions();

        allPossibleSolutions.removeAll(getAllSolutions());

        solutionRepository.saveAllAndFlush(allPossibleSolutions);

        long end = System.currentTimeMillis();

        return (end - start) / 1000;
    }
}

/*
try {
                    solutionRepository.saveAndFlush(answer);
                } catch (DataIntegrityViolationException dataIntegrityViolationException) {
                    if (!dataIntegrityViolationException.getMessage().contains("duplicate key value violates unique constraint")) {
                        throw dataIntegrityViolationException;
                    }
                }
 */
