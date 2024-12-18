package com.example.nagoyamesi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyamesi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>  {
	public Page<Category> findByNameLike(String keword, Pageable pageable);
}
