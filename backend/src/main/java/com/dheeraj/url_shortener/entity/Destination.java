package com.dheeraj.url_shortener.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="destinations",schema="shortener_v2")
public class Destination {

    @Id
    private UUID id;

    @Column(name="original_url",nullable=false)
    private String originalUrl;

    @Column(name="normalized_url",nullable=false)
    private String normalizedUrl;

    @Column(name="created_at",nullable=false,insertable = false,updatable = false)
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

    public void setNormalizedUrl(String normalizedUrl){
        this.normalizedUrl = normalizedUrl;
    }

    public String getOriginalUrl(){
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl){
        this.originalUrl = originalUrl;
    }

    public Instant getCreatedAt(){
        return createdAt;
    }
    
}
