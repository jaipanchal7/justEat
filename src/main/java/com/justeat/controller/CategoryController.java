package com.justeat.controller;

import com.justeat.dao.CategoryDao;
import com.justeat.dao.FoodDao;
import com.justeat.model.Category;
import com.justeat.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
	
	@Autowired
	private FoodDao foodDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@PostMapping("/addcategory")
	public ModelAndView addcategory(@ModelAttribute Category category) {
		ModelAndView  mv = new ModelAndView();
		
		categoryDao.save(category);
		
		mv.addObject("status", "Category Added Successfully!");
		mv.setViewName("index");
		
		return mv;
	}
	
	@GetMapping("/deletecategory")
	public ModelAndView deleteCategory(@RequestParam("categoryId") int categoryId) {
        ModelAndView  mv = new ModelAndView();
		
        Category category = null;
		Optional<Category> o = categoryDao.findById(categoryId);
		
		if(o.isPresent()) {
			category = o.get();
		}
		
		categoryDao.delete(category);
        
		List<Food> foods = foodDao.findByCategoryId(categoryId);
		
		for(Food food : foods) {
			foodDao.delete(food);
		}
		
		mv.addObject("status", "Category Deleted Successfully!");
		mv.setViewName("index");
		
		return mv;
	}
	
	@GetMapping("/category")
	public ModelAndView category(@RequestParam("categoryId") int  categoryId) {
		ModelAndView mv = new ModelAndView();
		List<Food> foods = new ArrayList<>();
		if(categoryId == 0) {
			foods = foodDao.findAll();
		}
		
		else {
			foods = foodDao.findByCategoryId(categoryId);
		}
		
		mv.addObject("foods", foods);
		mv.addObject("sentFromOtherSource", "yes");
		mv.setViewName("index");
		
		return mv;
	}

}
