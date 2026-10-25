    package com.dheeraj.url_shortener.entity;

    import java.time.Instant;
    import java.util.UUID;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.FetchType;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.Table;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;

    @Entity
    @Table(name="users",schema="shortener_v2")
    public class User{
        @Id
        private UUID id;

        @NotBlank(message="Email is required")
        @Email(message="Invalid email format")
        @Column(name="email",nullable=false,length=320)
        private String email;

        @Column(name="password_hash",nullable=false,length=255)
        private String passwordHash;

        @ManyToOne(fetch= FetchType.LAZY,optional=false)
        @JoinColumn(name="plan_id",nullable=false)
        private Plan plan;

        @Column(name="created_at",nullable=false,insertable=false,updatable=false)
        private Instant createdAt;

        @Column(name="updated_at",nullable=false,insertable=false,updatable=false)
        private Instant updatedAt;

        public User(){}

        public User(String email, String passwordHash, Plan plan){
            this.id=UUID.randomUUID();
            this.email=email;
            this.passwordHash = passwordHash;
            this.plan = plan;
        }

        public UUID getId(){
            return id;
        }

        public Instant getCreatedAt(){
            return createdAt;
        }

        public Instant getUpdatedAt(){
            return updatedAt;
        }

        public String getEmail(){
            return email;
        }

        public Plan getPlan(){
            return plan;
        }
    }