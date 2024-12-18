package com.example.nagoyamesi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.entity.Review;
import com.example.nagoyamesi.entity.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	public Page<Review> findByRestaurantOrderByCreatedAtDesc(Restaurant restaurant, Pageable pageable);
	public List<Review> findTop4ByRestaurantOrderByCreatedAtDesc(Restaurant restaurant);
	public long countByRestaurant(Restaurant restaurant);
	public Review findByRestaurantAndUser(Restaurant restaurant, User user);
	
	// メソッドの追加: ユーザーが特定の店舗に対してレビューを投稿しているかどうかを確認する
    boolean existsByUserAndRestaurant(User user, Restaurant restaurant);
    
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.restaurant = :restaurant")
    Double getAverageRatingByRestaurant(@Param("restaurant") Restaurant restaurant);
    
    @Query("SELECT COUNT(r) FROM Review r WHERE r.restaurant = :restaurant")
    Long getReviewCountByRestaurant(@Param("restaurant") Restaurant restaurant);
    
    void deleteByUserId(Long userId);
    
}
