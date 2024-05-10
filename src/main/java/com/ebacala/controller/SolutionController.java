package com.ebacala.controller;

import com.ebacala.entity.Solution;
import com.ebacala.exception.SolutionNotFoundException;
import com.ebacala.repository.SolutionRepository;
import com.ebacala.service.SolutionSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SolutionController {
    @Autowired
    private SolutionRepository solutionRepository;

    @GetMapping("/solutions")
    public List<Solution> getAllSolutions() {
        return solutionRepository.findAll();
    }

    @GetMapping("/solution/{id}")
    public Solution getSolutionById(@PathVariable Long pId) {
        return solutionRepository.findById(pId).orElseThrow(() -> new SolutionNotFoundException(pId));
    }

    @PostMapping("/solution")
    public Solution createSolution(@RequestBody Solution pSolution) {
        return solutionRepository.save(pSolution);
    }

    @PutMapping("/solution/{id}")
    public Solution updateSolution(@RequestBody Solution pSolution, @PathVariable Long pId) {
        return solutionRepository.findById(pSolution.getId()).map(solution -> {
            solution.setSolution(pSolution.getSolution());
            solution.setIsValid(SolutionSolver.solve(pSolution)); // TODO use algo to check solution
            solution.setModificationDate(new Date());

            return solutionRepository.save(solution);
        }).orElseGet(() -> {
            pSolution.setId(pId);
            return solutionRepository.save(pSolution);
        });
    }

    @DeleteMapping("/solution/{id}")
    public void deleteSolution(@PathVariable Long pId) {
        solutionRepository.deleteById(pId);
    }
}

// ? + 13 * ? / ? + ? + 12 * ? - ? - 11 + ? * ? : ? - 10 = 66