package com.example.nagoyamesi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nagoyamesi.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	public Page<Restaurant> findByNameLike(String keyword, Pageable pageable);
	public Page<Restaurant> findByNameLikeOrAddressLike(String nameKeyword, String addressKeyword, Pageable pageable);    
	public Page<Restaurant> findAllByOrderByCreatedAtDesc(Pageable pageable);
	public List<Restaurant> findTop6ByOrderByCreatedAtDesc();
	
	
	@Query("SELECT r FROM Restaurant r LEFT JOIN Review rv ON r.id = rv.restaurant.id GROUP BY r.id ORDER BY AVG(rv.rating) DESC")
    List<Restaurant> findTop6ByOrderByAverageRatingDesc(Pageable pageable);
	
	@Query("SELECT r FROM Restaurant r LEFT JOIN r.category c WHERE r.name LIKE %:keyword% OR c.name LIKE %:keyword%")
    Page<Restaurant> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
