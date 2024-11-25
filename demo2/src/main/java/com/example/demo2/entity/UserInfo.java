package com.example.demo2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Entity
@Data
@AllArgsConstructor // Used to generate a constructor with all arguments
@NoArgsConstructor  // Used to generate a no-argument constructor
public class UserInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Fixed spacing and added correct generation strategy
    private int id;
    
    private String name;
    
    private String email;
    
    private String password;
    
    private String roles;
}
