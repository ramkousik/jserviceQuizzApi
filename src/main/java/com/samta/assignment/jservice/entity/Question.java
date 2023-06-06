package com.samta.assignment.jservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.sql.Timestamp;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    private Long id;
    private String answer;
    private String question;
    private int value;
    private Timestamp airdate;
    private Timestamp created_at;
    private Timestamp updated_at;
    private int category_id;
    private int game_id;
    private Integer invalid_count;
    private String category_title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Timestamp getAirdate() {
        return airdate;
    }

    public void setAirdate(Timestamp airdate) {
        this.airdate = airdate;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public Integer getInvalid_count() {
        return invalid_count;
    }

    public void setInvalid_count(Integer invalid_count) {
        this.invalid_count = invalid_count;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }
}


