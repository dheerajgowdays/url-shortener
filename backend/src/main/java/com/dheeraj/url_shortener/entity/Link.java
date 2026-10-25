package com.dheeraj.url_shortener.entity;

import java.time.Instant;
import java.util.UUID;

import com.dheeraj.url_shortener.enums.LinkStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="links",schema="shortener_v2")
public class Link {
    @Id
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY,optional=true)
    @JoinColumn(name="user_id",nullable=true)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="destination_id",referencedColumnName="id",nullable=false)
    private Destination destination;

    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="short_code",referencedColumnName="short_code",nullable=false)
    private ShortCodeRegistry shortCodeRegistry;

    @Column(name="created_at",nullable=false,insertable=false,updatable=false)
    private Instant createdAt;

    @Column(name="expires_at",nullable=true)
    private Instant expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable=false,length=16)
    private LinkStatus status;


    public Link(){}

   public Link(User user, Destination destination, ShortCodeRegistry shortCodeRegistry, Instant expiresAt) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.destination = destination;
        this.shortCodeRegistry = shortCodeRegistry;
        this.expiresAt = expiresAt;
        this.status = LinkStatus.ACTIVE;
    }

    public UUID getId(){
        return id;
    }

    public User getUser(){
        return user;
    }

    public Destination getDestination(){
        return destination;
    }

    public ShortCodeRegistry getShortCodeRegistry(){
        return shortCodeRegistry;
    }

    public LinkStatus getLinkStatus(){
        return status;
    }

    public Instant getCreatedAt(){
        return createdAt;
    }

    public Instant getExpiresAt(){
        return expiresAt;
    }
}
