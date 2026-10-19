package com.dheeraj.url_shortener.entity;

import java.util.UUID;
import java.util.Instant;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Email;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;
import 

@Entity
@Table(name="users",schema="shortener_v2")
public class Users{
    @Id
    private UUID id;

    @Email
    private String email;

    @Column(name="password_hash",nullable=false,insertable=false,updatable=false)
    private String passwordHash;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id",nullable=false,referencedColumnName="id")
    private UUID planId;

    @Column(name="created_at",nullable=false,insertable=false,updatable=false)
    private Instant createdAt;

    @Column(name="updated_at",nullable=false,insertable=false,updatable=false)
    private Instant updatedAt;

    public Users(){}

    public Users(String email, String passwordHash, UUID planId){
        this.email=email;
        this.passwordHash = passwordHash;
        this.planId = planId;
    }

    public String getEmail(){
        return email;
    }
    public UUID getPlanId(){
        return planId;
    }
}