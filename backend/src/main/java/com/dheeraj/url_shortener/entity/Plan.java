package com.dheeraj.url_shortener.entity;

import java.time.Instant;
import java.util.UUID;

import com.dheeraj.url_shortener.enums.PlanName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="plans",schema="shortener_v2")
public class Plan {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name="name",nullable = false)
    private PlanName planName;

    @Column(name="created_at",nullable=false,insertable=false,updatable=false)
    private Instant createdAt;

    public Plan(){}

    public Plan(PlanName planName){
        this.id = UUID.randomUUID();
        this.planName = planName;
    }

    public UUID getId(){
        return id;
    }

    public PlanName getPlanName(){
        return planName;
    }

    public Instant getCreatedAt(){
        return createdAt;
    }

    
}
