package com.ebacala.solution;

import com.ebacala.responsehelper.JsonMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class SolutionController {
    @Autowired
    private SolutionService solutionService;

    @Autowired
    private SolutionSolver solutionSolver;

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(getErrorsMap(errors));
    }

    @ExceptionHandler(value = {InvalidSolutionException.class})
    public ResponseEntity<String> handleInvalidSolution(InvalidSolutionException exception) {
        return ResponseEntity.badRequest().body(new JsonMessage(exception.getMessage()).getBody());
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("message", errors);
        return errorResponse;
    }

    @GetMapping("/solutions")
    public ResponseEntity<List<Solution>> getAllSolutions() {
        return ResponseEntity.ok(solutionService.getAllSolutions());
    }

    @GetMapping("/solution/{id}")
    public ResponseEntity<Solution> getSolutionById(@PathVariable Long id) {
        Optional<Solution> solution = solutionService.getSolutionById(id);

        if (solution.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(solution.get());
    }

    @PostMapping("/generate-all-solutions")
    public ResponseEntity<String> generateAllSolution() {
        long durationInSeconds = solutionService.generateAllPossibleSolutions();

        JsonMessage jsonMessage = new JsonMessage("The generation of all the solutions took " + durationInSeconds + " seconds.");

        return ResponseEntity.ok(jsonMessage.getBody());
    }

    @PostMapping("/solution")
    public ResponseEntity<Solution> createSolution(@RequestBody @Valid Solution solution) {
        if (!solution.containsDifferentUnknowns()) {
            throw new InvalidSolutionException();
        }

        return ResponseEntity.ok(solutionService.createSolution(solution));
    }

    @PutMapping("/solution/{id}")
    public Solution updateSolution(@PathVariable Long id, @RequestBody @Valid Solution solution) {
        return solutionService.updateSolution(id, solution);
    }

    @DeleteMapping("/solution/{id}")
    public void deleteSolution(@PathVariable Long id) {
        solutionService.deleteSolutionById(id);
    }

    @DeleteMapping("/solutions")
    public void deleteAllSolutions() {
        solutionService.deleteAllSolutions();
    }
}