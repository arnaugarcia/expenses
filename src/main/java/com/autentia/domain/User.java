package com.autentia.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

}
