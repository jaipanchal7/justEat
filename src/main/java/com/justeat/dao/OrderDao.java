package com.justeat.dao;

import com.justeat.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Orders , Integer> {
	
	List<Orders> findByOrderId(String orderId);
	List<Orders> findByUserId(int userId);
	List<Orders> findByOrderDate(String orderDate);
	List<Orders> findByOrderDateAndUserId(String orderDate, int userId);

}
