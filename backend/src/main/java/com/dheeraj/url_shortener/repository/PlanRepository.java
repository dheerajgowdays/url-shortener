package com.dheeraj.url_shortener.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dheeraj.url_shortener.entity.Plan;

public interface  PlanRepository extends JpaRepository<Plan,UUID>{
    
}
