package com.farmbazaar.model.repository;

import com.farmbazaar.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Add custom query methods if needed
}