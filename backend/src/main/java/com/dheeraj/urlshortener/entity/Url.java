package com.dheeraj.urlshortener.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "urls",
    indexes = {
        @Index(
            name = "idx_urls_short_code",
            columnList = "short_code",
            unique = true
        )
    }
)
public class Url{

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "url_id_generator"
        )
    @SequenceGenerator(
         name = "url_id_generator",
         sequenceName = "url_id_sequence",
         allocationSize = 1
        )
    private Long id;
    @Column(
        name = "short_code",
        nullable = false,
        unique = true,
        length =16
    )
    private String shortCode;
    @Column(
        name = "original_url",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String originalUrl;
    @Column(
        name ="created_at",
        nullable = false
    )
    private Instant createdAt;

    protected Url(){

    }

    public Url(String shortCode,String originalUrl,Instant createdAt){
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
    }
    public Long getId(){
        return id;
    }   
    public String getShortCode(){
        return shortCode;
    }
    public String getOriginalUrl(){
        return originalUrl;
    }
    public Instant getCreatedAt(){
        return createdAt;
    }

}