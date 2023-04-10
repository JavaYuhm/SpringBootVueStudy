package com.example.study_springbootvue.repository;

import com.example.study_springbootvue.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
