package com.dheeraj.url_shortener.entity;

import java.util.UUID;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="destinations",schema="shortener_v2")
public class Destination {

    @Id
    private UUID id;

    @Column(name="original_url",nullable=false)
    private String originalUrl;

    @Column(name="normalized_url",nullable=false)
    private String normalizedUrl;

    @Column(name="created_at",nullable=false)
    private Instant createdAt;

    public Destination(){}

    public Destination(String originalUrl,String normalizedUrl){
        this.id = UUID.randomUUID();
        this.originalUrl = originalUrl;
        this.normalizedUrl = normalizedUrl;
    }

    public UUID getId(){
        return id;
    }

    public String getNormalizedUrl(){
        return normalizedUrl;
    }

    public String getOriginalUrl(){
        return originalUrl;
    }

    
}
