package com.samta.assignment.jservice.entity;

import jakarta.persistence.*;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;
    private String question;
    private Long value;
//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime airdate;
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;
    @Column(insertable = false, updatable = false)
    private Long category_id;
    private Integer game_id;
    private Integer invalid_count;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public LocalDateTime getAirdate() {
        return airdate;
    }

    public void setAirdate(LocalDateTime airdate) {
        this.airdate = airdate;
    }

    public OffsetDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(OffsetDateTime  created_at) {
        this.created_at = created_at;
    }

    public OffsetDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(OffsetDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public Integer getInvalid_count() {
        return invalid_count;
    }

    public void setInvalid_count(Integer invalid_count) {
        this.invalid_count = invalid_count;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}


