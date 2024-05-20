package com.ebacala.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private AnswerSolver answerSolver;

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public List<Answer> getFilteredAnswers(Example<Answer> filteredAnswer) {
        return answerRepository.findAll(filteredAnswer);
    }

    public Optional<Answer> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    public Answer createAnswer(Answer answer) {
        answer.setIsValid(answerSolver.isAnswerValid(answer));

        return answerRepository.saveAndFlush(answer);
    }

    public Answer updateAnswer(Long id, Answer answer) {
        Answer foundAnswer = answerRepository.findById(id).orElseGet(Answer::new);

        foundAnswer.setId(id);
        foundAnswer.setA(answer.getA());
        foundAnswer.setB(answer.getB());
        foundAnswer.setC(answer.getC());
        foundAnswer.setD(answer.getD());
        foundAnswer.setE(answer.getE());
        foundAnswer.setF(answer.getF());
        foundAnswer.setG(answer.getG());
        foundAnswer.setH(answer.getH());
        foundAnswer.setI(answer.getI());
        foundAnswer.setIsValid(answerSolver.isAnswerValid(foundAnswer));

        return answerRepository.saveAndFlush(foundAnswer);
    }

    public void deleteAnswerById(Long id) {
        answerRepository.deleteById(id);
    }

    public void deleteAllAnswers() {
        answerRepository.deleteAllInBatch();
    }

    public Long generateAllPossibleSolutions() {
        long start = System.currentTimeMillis();

        List<Answer> allPossibleSolutions = answerSolver.generateAllPossibleSolutions();

        allPossibleSolutions.removeAll(getAllAnswers());

        answerRepository.saveAllAndFlush(allPossibleSolutions);

        long end = System.currentTimeMillis();

        return (end - start) / 1000;
    }
}