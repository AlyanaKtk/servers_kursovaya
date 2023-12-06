package com.example.servers_kursovaya.model;


import jakarta.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class baseModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public boolean isNew() {
        return this.id == null;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
