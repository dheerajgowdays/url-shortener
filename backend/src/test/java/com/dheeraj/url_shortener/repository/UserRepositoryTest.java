package com.dheeraj.url_shortener.repository;

import com.dheeraj.url_shortener.repository.UserRepository;
import com.dheeraj.rul_shortener.repository.PlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jap.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import org.junit.jupiter.api.Test;
@DataJpaTest
@AutoConfgiureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Test
    void shouldGetAllSeededUsers(){
        var users = userRepository.findAll();

        assertTest(users).
    }
}