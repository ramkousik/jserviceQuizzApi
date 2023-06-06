package com.samta.assignment.jservice.service.impl;

import com.samta.assignment.jservice.entity.Category;
import com.samta.assignment.jservice.entity.Question;
import com.samta.assignment.jservice.repository.CategoryRepository;
import com.samta.assignment.jservice.repository.QuestionRepository;
import com.samta.assignment.jservice.service.IQuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, CategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
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
        for (Question question : questions) {
            // Check if the category exists
            Long categoryId = question.getCategory().getId();
            Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                question.setCategory(category);
            } else {
                // Create and save the category if it doesn't exist
                Category newCategory = new Category();
                newCategory.setId(categoryId);
                // Set other category properties if available
                // ...

                // Save the new category
                categoryRepository.save(newCategory);

                // Associate the new category with the question
                question.setCategory(newCategory);
            }
        }
        return questionRepository.saveAll(questions);
    }


    @Override
    public Optional<Question> getQuestionById(Long id) {
         return questionRepository.findById(id);
    }
}
