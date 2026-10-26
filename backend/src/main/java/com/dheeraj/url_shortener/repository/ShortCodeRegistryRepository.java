package com.dheeraj.url_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dheeraj.url_shortener.entity.ShortCodeRegistry;

public interface ShortCodeRegistryRepository extends JpaRepository<ShortCodeRegistry,String>{
    
}
