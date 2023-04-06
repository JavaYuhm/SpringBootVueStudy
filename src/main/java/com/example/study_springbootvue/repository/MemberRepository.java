package com.example.study_springbootvue.repository;

import com.example.study_springbootvue.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmailAndAndPassword(String email, String password);
}
