package com.example.study_springbootvue.controller;

import com.example.study_springbootvue.entity.Item;
import com.example.study_springbootvue.entity.Member;
import com.example.study_springbootvue.repository.ItemRepository;
import com.example.study_springbootvue.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/api/member/login")
    public int login(@RequestBody Map<String, String> params){
        Optional<Member> optionalMember = memberRepository.findByEmailAndAndPassword(params.get("email"), params.get("password"));
        Member member;
        if(optionalMember.isPresent()){
            member = optionalMember.get();
            log.info("멤버 로그인");

            return member.getId();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
