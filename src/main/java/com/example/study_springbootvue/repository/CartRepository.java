package com.example.study_springbootvue.repository;

import com.example.study_springbootvue.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByMemberId(int memberId);
    Cart findByMemberIdAndItemId(int memberId, int itemId);
}
