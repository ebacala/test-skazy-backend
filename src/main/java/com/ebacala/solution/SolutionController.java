package com.ebacala.solution;

import com.ebacala.responsehelper.JsonMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public ResponseEntity<List<Solution>> getAllSolutions(
            @RequestParam(required = false) Integer a,
            @RequestParam(required = false) Integer b,
            @RequestParam(required = false) Integer c,
            @RequestParam(required = false) Integer d,
            @RequestParam(required = false) Integer e,
            @RequestParam(required = false) Integer f,
            @RequestParam(required = false) Integer g,
            @RequestParam(required = false) Integer h,
            @RequestParam(required = false) Integer i,
            @RequestParam(required = false) Boolean isValid) {

        // Check if any parameter is provided
        if (Stream.of(a, b, c, d, e, f, g, h, i, isValid).allMatch(Objects::isNull)) {
            return ResponseEntity.ok(solutionService.getAllSolutions());
        }

        Solution filteredSolution = new Solution();
        if (a != null) filteredSolution.setA(a);
        if (b != null) filteredSolution.setB(b);
        if (c != null) filteredSolution.setC(c);
        if (d != null) filteredSolution.setD(d);
        if (e != null) filteredSolution.setE(e);
        if (f != null) filteredSolution.setF(f);
        if (g != null) filteredSolution.setG(g);
        if (h != null) filteredSolution.setH(h);
        if (i != null) filteredSolution.setI(i);
        if (isValid != null) filteredSolution.setIsValid(isValid);
        
        // Use ExampleMatcher to create a flexible filter
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();

        return ResponseEntity.ok(solutionService.getFilteredSolutions(Example.of(filteredSolution, matcher)));
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