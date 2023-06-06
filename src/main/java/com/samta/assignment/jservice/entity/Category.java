package com.samta.assignment.jservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    private Long id;

    private String title;
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;
    private Integer clues_count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OffsetDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(OffsetDateTime created_at) {
        this.created_at = created_at;
    }

    public OffsetDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(OffsetDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getClues_count() {
        return clues_count;
    }

    public void setClues_count(Integer clues_count) {
        this.clues_count = clues_count;
    }
}
