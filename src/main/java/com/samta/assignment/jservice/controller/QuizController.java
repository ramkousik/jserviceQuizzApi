package com.samta.assignment.jservice.controller;

import com.samta.assignment.jservice.entity.Question;
import com.samta.assignment.jservice.service.IQuestionService;
import com.samta.assignment.jservice.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class QuizController {
    private final QuestionServiceImpl questionService;

    @Autowired
    public QuizController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/store-random-questions")
    public ResponseEntity<String> storeRandomQuestions() {
        List<Question> questions = questionService.getRandomQuestions();
        if (questions != null && !questions.isEmpty()) {
            List<Question> savedQuestions = questionService.saveQuestions(questions);
            if (savedQuestions != null && !savedQuestions.isEmpty()) {
                return ResponseEntity.ok("Random questions stored in the database.");
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to store random questions.");
    }
    @GetMapping("/play")
    public ResponseEntity<QuestionDto> play() {
        Question randomQuestion = questionService.getRandomQuestion();
        if (randomQuestion != null) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setQuestion_id(randomQuestion.getId());
            questionDto.setQuestion(randomQuestion.getQuestion());
            return ResponseEntity.ok(questionDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/next")
    public ResponseEntity<NextQuestionResponse> next(@RequestBody AnswerDto answerDto) {
        Long questionId = answerDto.getQuestion_id();
        String answer = answerDto.getAnswer();

        // Retrieve the previous question and its correct answer from the database
        Question previousQuestion = questionService.getQuestionById(questionId).orElse(null);

        if (previousQuestion != null) {
            String correctAnswer = previousQuestion.getAnswer();

            // Fetch a new random question from the database
            Question randomQuestion = questionService.getRandomQuestion();

            if (randomQuestion != null) {
                QuestionDto questionDto = new QuestionDto();
                questionDto.setQuestion_id(randomQuestion.getId());
                questionDto.setQuestion(randomQuestion.getQuestion());

                NextQuestionResponse nextQuestionResponse = new NextQuestionResponse();
                nextQuestionResponse.setCorrect_answer(correctAnswer);
                nextQuestionResponse.setNext_question(questionDto);

                return ResponseEntity.ok(nextQuestionResponse);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // DTO classes

    public static class QuestionDto {
        private Long question_id;
        private String question;

        public Long getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(Long question_id) {
            this.question_id = question_id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
    }

    public static class AnswerDto {
        private Long question_id;
        private String answer;

        public Long getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(Long question_id) {
            this.question_id = question_id;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }

    public static class NextQuestionResponse {
        private String correct_answer;
        private QuestionDto next_question;

        public String getCorrect_answer() {
            return correct_answer;
        }

        public void setCorrect_answer(String correct_answer) {
            this.correct_answer = correct_answer;
        }

        public QuestionDto getNext_question() {
            return next_question;
        }

        public void setNext_question(QuestionDto next_question) {
            this.next_question = next_question;
        }
    }
}