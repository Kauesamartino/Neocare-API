package com.neocare.api.domain.model;

import java.util.HashSet;
import java.util.Set;

public class Role {

    private String name;

    private String description;

    private Set<Credenciais> credenciais = new HashSet<>();

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Credenciais> getAuthUsers() {
        return credenciais;
    }

    public Role(String name, String description, Set<Credenciais> credenciais) {
        this.name = name;
        this.description = description;
        this.credenciais = credenciais;
    }

    public Role(String name){
        this.name = name;
    }

    public Role() {
    }

}
