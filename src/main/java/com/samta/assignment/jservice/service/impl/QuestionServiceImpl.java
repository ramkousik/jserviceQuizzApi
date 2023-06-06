package com.samta.assignment.jservice.service.impl;

import com.samta.assignment.jservice.entity.Question;
import com.samta.assignment.jservice.repository.QuestionRepository;
import com.samta.assignment.jservice.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements IQuestionService {

    private final QuestionRepository questionRepository;
    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getRandomQuestions() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://jservice.io/api/random?count=5";
        Question[] questionsArray = restTemplate.getForObject(apiUrl, Question[].class);
        List<Question> questions = Arrays.asList(questionsArray);
        return saveQuestions(questions);
    }



    @Override
    public Question getRandomQuestion() {
        Optional<Question> randomQuestion = questionRepository.findRandomQuestion();
        return randomQuestion.orElse(null);
    }


    public List<Question> saveQuestions(List<Question> questions) {
        List<Question> savedQuestions = new ArrayList<>();
        for (Question question : questions) {
            savedQuestions.add(questionRepository.save(question));
        }
        return savedQuestions;
    }


    @Override
    public Optional<Question> getQuestionById(Long id) {
         return questionRepository.findById(id);
    }
}
