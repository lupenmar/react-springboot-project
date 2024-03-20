package com.lupenmar.springtodo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import jakarta.persistence.*;


@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String description;

}