package com.justeat.dao;

import com.justeat.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
	
	List<Cart> findByUserId(int userId);
	Long countByUserId(int userId);

}
