package com.example.study_springbootvue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int memberId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(length = 20, nullable = false)
    private String payment;

    @Column(length = 40, nullable = false)
    private String cardNumber;

    @Column(length = 500, nullable = false)
    private String items;

}

