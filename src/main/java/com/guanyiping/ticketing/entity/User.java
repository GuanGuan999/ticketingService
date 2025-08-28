package com.guanyiping.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    @Column(nullable = false,name = "username")
    private String userName;

    @Column(nullable = false,name = "password")
    private String passWord;

    @Column(nullable = false,unique = true)
    private String email;

}
