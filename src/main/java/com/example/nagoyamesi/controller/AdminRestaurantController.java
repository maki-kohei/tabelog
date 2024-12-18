package com.example.nagoyamesi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyamesi.entity.Category;
import com.example.nagoyamesi.entity.Restaurant;
import com.example.nagoyamesi.form.RestaurantEditForm;
import com.example.nagoyamesi.form.RestaurantRegisterForm;
import com.example.nagoyamesi.repository.CategoryRepository;
import com.example.nagoyamesi.repository.RestaurantRepository;
import com.example.nagoyamesi.service.RestaurantService;



@Controller
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {
	private final RestaurantRepository  restaurantRepository;
	private final RestaurantService restaurantService;
	private final CategoryRepository categoryRepository;
	
	public AdminRestaurantController(RestaurantRepository restaurantRepository, RestaurantService restaurantService, CategoryRepository categoryRepository) {
        this.restaurantRepository = restaurantRepository; 
        this.restaurantService = restaurantService;
        this.categoryRepository = categoryRepository;
      
    }
	//管理者
	//店舗一覧
	@GetMapping
    public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, 
    								 @RequestParam(name = "keyword", required = false) String keyword) {
        Page<Restaurant> restaurantPage; 
        
        if (keyword != null && !keyword.isEmpty()) {
        	restaurantPage = restaurantRepository.findByNameLike("%" + keyword + "%", pageable);                
        } else {
        	restaurantPage = restaurantRepository.findAll(pageable);
        }  
        model.addAttribute("restaurantPage", restaurantPage);
        model.addAttribute("keyword", keyword);
        
        return "admin/restaurants/index";
    }  
	//店舗詳細
    @GetMapping("/{restaurantId}")
    public String show(@PathVariable("restaurantId") Integer restaurantId, Model model) {
    	Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
        
        model.addAttribute("restaurant", restaurant);
        
        return "admin/restaurants/show";
    }    
    //店舗登録
    @GetMapping("/register")
    public String register(Model model) {
    	List<Category> categors = categoryRepository.findAll();
    	List<String> times = new ArrayList<>();
    	for (int hour = 0; hour < 24; hour++) {
    	    times.add(String.format("%02d:00", hour));
    	    times.add(String.format("%02d:30", hour));
    	}
    	
    	model.addAttribute("times", times);
    	model.addAttribute("restaurantRegisterForm", new RestaurantRegisterForm());
    	model.addAttribute("categors", categors);
    	return "admin/restaurants/register";
    }  
    //店舗登録
    @PostMapping("/create")
	public String create(@ModelAttribute @Validated RestaurantRegisterForm restaurantRegisterForm, BindingResult bindingResult,RedirectAttributes redirectAttributes, Model model) {
    	List<Category> categors = categoryRepository.findAll();
		if(bindingResult.hasErrors()) {
			List<String> times = new ArrayList<>();
	    	for (int hour = 0; hour < 24; hour++) {
	    	    times.add(String.format("%02d:00", hour));
	    	    times.add(String.format("%02d:30", hour));
	    	}
	    	
	    	model.addAttribute("categors", categors);
	    	model.addAttribute("times", times);
			return "/admin/restaurants/register";
		}
		

		restaurantService.create(restaurantRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "店舗を登録しました。");
		
		return "redirect:/admin/restaurants";
	}
    //店舗編集(データ表示データ受け渡し)
    @GetMapping("/{restaurantId}/edit")
	public String edit(@PathVariable( "restaurantId") Integer restaurantId, Model model) {
		Restaurant restaurant = restaurantRepository.getReferenceById(restaurantId);
		String imageName = restaurant.getImageName();
		RestaurantEditForm restaurantEditForm = new RestaurantEditForm(
													restaurant.getId(),
													restaurant.getName(),
													null,
													restaurant.getCategory(),
													restaurant.getOpeningTime(),
													restaurant.getClosingTime(),
													restaurant.getRegularHoliday(),
													restaurant.getLowestPrice(),
													restaurant.getHighestPrice(),
													restaurant.getDescription(),
													restaurant.getPostalCode(),
													restaurant.getAddress(),
													restaurant.getPhoneNumber());	
		List<Category> categors = categoryRepository.findAll();
		List<String> times = new ArrayList<>();
    	for (int hour = 0; hour < 24; hour++) {
    	    times.add(String.format("%02d:00", hour));
    	    times.add(String.format("%02d:30", hour));
    	}
    	
    	model.addAttribute("categors", categors);
    	model.addAttribute("times", times);
		model.addAttribute("imageName", imageName);
		model.addAttribute("restaurantEditForm", restaurantEditForm);
		
		return "admin/restaurants/edit";
		
		}
    //編集登録
    @PostMapping("/{restaurantId}/update")
	public String update(@ModelAttribute @Validated RestaurantEditForm restaurantEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
    	List<Category> categors = categoryRepository.findAll();
		if (bindingResult.hasErrors()) {
			List<String> times = new ArrayList<>();
	    	for (int hour = 0; hour < 24; hour++) {
	    	    times.add(String.format("%02d:00", hour));
	    	    times.add(String.format("%02d:30", hour));
	    	}
	    	
	    	model.addAttribute("categors", categors);
	    	model.addAttribute("times", times);
			return "admin/restaurants/edit";
		}
		
		restaurantService.update(restaurantEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "店舗情報を編集しました。");
		
		return "redirect:/admin/restaurants";
	}
    //削除
    @PostMapping("/{restaurantId}/delete")
	public String delete(@PathVariable("restaurantId") Integer restaurantId, RedirectAttributes redirectAttributes) {
		restaurantRepository.deleteById(restaurantId);
		
		redirectAttributes.addFlashAttribute("successMessage", "店舗情報を削除しました。");
		
		return "redirect:/admin/restaurants";
	}
    
}
