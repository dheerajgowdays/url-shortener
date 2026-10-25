package com.dheeraj.url_shortener.entity;

import java.time.Instant;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="short_code_registry",schema="shortener_v2")
public class ShortCodeRegistry{

    @Id
    @Column(name="short_code",length=32)
    private String shortCode;

    @Column(name="created_at",nullable=false,updatable=false,insertable=false)
    private Instant createdAt;

    public ShortCodeRegistry(){}

    public ShortCodeRegistry(String shortCode){
        this.shortCode = shortCode;
    }

    public String getShortCode(){
        return shortCode;
    }

    public void setShortCode(String shortCode){
        this.shortCode = shortCode;
    }

    public Instant getCreatedAt(){
        return createdAt;
    }
}