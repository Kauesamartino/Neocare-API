package com.neocare.api.infrastructure.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class JpaRoleEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<JpaCredenciaisEntity> users = new HashSet<>();

    public JpaRoleEntity() {
    }

    public JpaRoleEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<JpaCredenciaisEntity> getUsers() {
        return users;
    }
}
