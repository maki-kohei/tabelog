package com.example.nagoyamesi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.repository.RestaurantRepository;
import com.example.nagoyamesi.service.ReviewService;

@Controller
public class HomeController {
	private final RestaurantRepository restaurantRepository;
	private final ReviewService reviewService;
	
	public HomeController(RestaurantRepository restaurantRepository, ReviewService reviewService) {
		this.restaurantRepository = restaurantRepository;
		this.reviewService = reviewService;

	}
	
	@GetMapping("/")
	public String index(Model model) {
		List<Restaurant> newRestaurantList = restaurantRepository.findTop6ByOrderByCreatedAtDesc();
        List<Restaurant> topRatedRestaurantList = reviewService.getTop6RestaurantsByRating();
        
        Map<Integer, Double> averageRatings = new HashMap<>();
        Map<Integer, Long> reviewCounts = new HashMap<>();

        for (Restaurant restaurant : newRestaurantList) {
            Double averageRating = reviewService.getAverageRatingByRestaurant(restaurant);
            Long reviewCount = reviewService.getReviewCountByRestaurant(restaurant);

            averageRatings.put(restaurant.getId(), averageRating);
            reviewCounts.put(restaurant.getId(), reviewCount);
        }
        
        for (Restaurant restaurant : topRatedRestaurantList) {
            Double averageRating = reviewService.getAverageRatingByRestaurant(restaurant);
            Long reviewCount = reviewService.getReviewCountByRestaurant(restaurant);
            
            averageRatings.put(restaurant.getId(), averageRating);
            reviewCounts.put(restaurant.getId(), reviewCount);
        }

        model.addAttribute("averageRatings", averageRatings);
        model.addAttribute("reviewCounts", reviewCounts);
        model.addAttribute("restaurantList", newRestaurantList); // 新規掲載店舗
        model.addAttribute("topRatedRestaurants", topRatedRestaurantList); // 評価の高い店舗
        return "index";
    }
	 
}
