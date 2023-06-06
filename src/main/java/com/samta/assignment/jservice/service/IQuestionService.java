package com.samta.assignment.jservice.service;

import com.samta.assignment.jservice.entity.Question;

import java.util.List;
import java.util.Optional;

public interface IQuestionService {
    public List<Question> getRandomQuestions();

    public Question getRandomQuestion();
    public Optional<Question> getQuestionById(Long id);
}
