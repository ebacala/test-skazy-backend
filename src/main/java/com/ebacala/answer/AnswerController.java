package com.ebacala.answer;

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
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerSolver answerSolver;

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(getErrorsMap(errors));
    }

    @ExceptionHandler(value = {InvalidAnswerException.class})
    public ResponseEntity<String> handleInvalidAnswer(InvalidAnswerException exception) {
        return ResponseEntity.badRequest().body(new JsonMessage(exception.getMessage()).getBody());
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("message", errors);
        return errorResponse;
    }

    @GetMapping("/answers")
    public ResponseEntity<List<Answer>> getAllAnswers(
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
            return ResponseEntity.ok(answerService.getAllAnswers());
        }

        Answer filteredAnswer = new Answer();
        if (a != null) filteredAnswer.setA(a);
        if (b != null) filteredAnswer.setB(b);
        if (c != null) filteredAnswer.setC(c);
        if (d != null) filteredAnswer.setD(d);
        if (e != null) filteredAnswer.setE(e);
        if (f != null) filteredAnswer.setF(f);
        if (g != null) filteredAnswer.setG(g);
        if (h != null) filteredAnswer.setH(h);
        if (i != null) filteredAnswer.setI(i);
        if (isValid != null) filteredAnswer.setIsValid(isValid);
        
        // Use ExampleMatcher to create a flexible filter
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();

        return ResponseEntity.ok(answerService.getFilteredAnswers(Example.of(filteredAnswer, matcher)));
    }

    @GetMapping("/answer/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id) {
        Optional<Answer> answer = answerService.getAnswerById(id);

        if (answer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(answer.get());
    }

    @PostMapping("/generate-all-solutions")
    public ResponseEntity<String> generateAllAnswers() {
        long durationInSeconds = answerService.generateAllPossibleSolutions();

        JsonMessage jsonMessage = new JsonMessage("The generation of all the solutions took " + durationInSeconds + " seconds.");

        return ResponseEntity.ok(jsonMessage.getBody());
    }

    @PostMapping("/answer")
    public ResponseEntity<Answer> createAnswer(@RequestBody @Valid Answer answer) {
        if (!answer.containsDifferentUnknowns()) {
            throw new InvalidAnswerException();
        }

        return ResponseEntity.ok(answerService.createAnswer(answer));
    }

    @PutMapping("/answer/{id}")
    public Answer updateAnswer(@PathVariable Long id, @RequestBody @Valid Answer answer) {
        return answerService.updateAnswer(id, answer);
    }

    @DeleteMapping("/answer/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswerById(id);
    }

    @DeleteMapping("/answers")
    public void deleteAllAnswers() {
        answerService.deleteAllAnswers();
    }
}