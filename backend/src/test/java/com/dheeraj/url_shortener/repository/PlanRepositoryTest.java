package com.dheeraj.url_shortener.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.dheeraj.url_shortener.entity.Plan;
import com.dheeraj.url_shortener.enums.PlanName;
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class PlanRepositoryTest{
    @Autowired
    private  PlanRepository planRepository;

    @Test
    void shouldGetAllSeededPlans(){
        var plans = planRepository.findAll();

        assertThat(plans).hasSize(3);

        Set<PlanName> names = plans.stream()
                .map(Plan::getPlanName)
                .collect(Collectors.toSet());

        assertThat(names).containsExactlyInAnyOrder(
                PlanName.FREE,
                PlanName.PREMIUM,
                PlanName.BUSINESS
        );

        assertThat(plans)
                .allSatisfy(plan ->
                        assertThat(plan.getId()).isNotNull()
                );
    }
}