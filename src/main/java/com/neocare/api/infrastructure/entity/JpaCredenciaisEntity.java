package com.neocare.api.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "credenciais")
public class JpaCredenciaisEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "credenciais_role",
            joinColumns = @JoinColumn(name = "credenciais_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<JpaRoleEntity> roles = new HashSet<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public JpaCredenciaisEntity() {
    }

    public JpaCredenciaisEntity(String username, String password, String fullName, Set<JpaRoleEntity> roles, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Set<JpaRoleEntity> getRoles() {
        return roles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setRoles(Set<JpaRoleEntity> roles) {
        this.roles = roles;
    }
}
