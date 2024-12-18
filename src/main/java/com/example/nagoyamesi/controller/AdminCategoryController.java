package com.example.nagoyamesi.controller;

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
import com.example.nagoyamesi.form.CategoryEditForm;
import com.example.nagoyamesi.form.CategoryRegisterForm;
import com.example.nagoyamesi.repository.CategoryRepository;
import com.example.nagoyamesi.service.CategoryService;


@Controller
@RequestMapping("/admin/categorys")
public class AdminCategoryController {
	private final CategoryRepository categoryRepository;
	private final CategoryService categoryService;
	
	public AdminCategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
		this.categoryRepository = categoryRepository;
		this.categoryService = categoryService;
		
	}
	//カテゴリ一覧
	@GetMapping
	public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {
		Page<Category> categoryPage;
		
		if (keyword != null && !keyword.isEmpty()) {
			categoryPage = categoryRepository.findByNameLike("%" + keyword + "%", pageable);                
        } else {
        	categoryPage = categoryRepository.findAll(pageable);
        }  
		
		model.addAttribute("categoryPage", categoryPage);
		model.addAttribute("categoryPage", categoryPage);
		
		return "admin/categorys/index";
	}
	//カテゴリ登録遷移
	 @GetMapping("/register")
	    public String register(Model model) {
	    	
	    	model.addAttribute("categoryRegisterForm", new CategoryRegisterForm());
	    	
	    	return "admin/categorys/register";
	    }  
	    //カテゴリ登録
	    @PostMapping("/create")
		public String create(@ModelAttribute @Validated CategoryRegisterForm categoryRegisterForm, BindingResult bindingResult,RedirectAttributes redirectAttributes) {
			if(bindingResult.hasErrors()) {
				return "admin/categorys/register";
			}
			
			categoryService.create(categoryRegisterForm);
			redirectAttributes.addFlashAttribute("successMessage", "カテゴリーを登録しました。");
			
			return "redirect:/admin/categorys";
		}
	    //店舗編集（データ表示データ受け渡し）
	    @GetMapping("/{categoryId}/edit")
	    public String edit(@PathVariable("categoryId") Integer categoryId, Model model) {
	        Category category = categoryRepository.getReferenceById(categoryId);
	        CategoryEditForm categoryEditForm = new CategoryEditForm(category.getId(), category.getName());

	        model.addAttribute("categoryEditForm", categoryEditForm);

	        return "admin/categorys/edit";
	    }
	    //編集登録
	    @PostMapping("/{categoryId}/update")
	    public String update(@ModelAttribute @Validated CategoryEditForm categoryEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
	        if (bindingResult.hasErrors()) {
	            return "admin/categorys/edit";
	        }

	        categoryService.update(categoryEditForm);
	        redirectAttributes.addFlashAttribute("successMessage", "カテゴリー名を編集しました。");

	        return "redirect:/admin/categorys";
	    }
	    
	    @PostMapping("/{categoryId}/delete")
		public String delete(@PathVariable("categoryId") Integer categoryId, RedirectAttributes redirectAttributes) {
	    	categoryRepository.deleteById(categoryId);
			
			redirectAttributes.addFlashAttribute("successMessage", "カテゴリー情報を削除しました。");
			
			return "redirect:/admin/categorys";
		}



}
