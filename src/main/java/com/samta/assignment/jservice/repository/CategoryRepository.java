package com.samta.assignment.jservice.repository;

import com.samta.assignment.jservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
