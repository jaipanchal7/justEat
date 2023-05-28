package com.justeat.dao;

import com.justeat.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodDao extends JpaRepository<Food, Integer> {
	
	List<Food> findByNameContainingIgnoreCase(String name);
	List<Food> findByCategoryId(int categpryId);
	
	@Query(value="delete from Food f where f.categoryId = :categoryId")
	void deleteProductByCategoryId(@Param("categoryId") int categoryId);

}
