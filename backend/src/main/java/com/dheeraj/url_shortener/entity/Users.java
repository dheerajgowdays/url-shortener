package com.dheeraj.url_shortener.entity;

import java.util.UUID;
import java.util.Instant;

import jakarta.validation.constraints.Email;

@Entity
@Table(name="users",schema="shortener_v2")
public class Users{
    @Id
    private UUID id;

    @Email
    private String email;

    private String passwordHash;

    
    private UUID planId;

    @Column(name="created_at",nullable=false,insertable=false,updatable=false)
    private Instant createdAt;

    @Column(name="updated_at",nullable=false,insertable=false,updatable=false)
    private Instant updatedAt;
}