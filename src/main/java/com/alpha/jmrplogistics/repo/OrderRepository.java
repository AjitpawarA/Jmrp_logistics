package com.alpha.jmrplogistics.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.jmrplogistics.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
