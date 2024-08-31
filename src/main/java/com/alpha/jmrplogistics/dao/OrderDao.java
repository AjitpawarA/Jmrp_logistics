package com.alpha.jmrplogistics.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.jmrplogistics.entity.Order;
import com.alpha.jmrplogistics.repo.OrderRepository;

@Repository
public class OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }
    
    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }
    
    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}
}
