package com.ebacala.service;

import com.ebacala.entity.Solution;
import org.springframework.stereotype.Service;

@Service
public class SolutionSolver {
    public static boolean solve(Solution solution) {
        System.out.println(solution);

        return true;
    }
}
