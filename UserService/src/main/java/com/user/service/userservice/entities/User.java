package com.user.service.userservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "ID")
    private String userId;
    @Column(name = "NAME" ,  length = 15)
    private String name ;
    @Column(name = "EMAIL" , unique = true)
    private String email;
    @Column(name = "ABOUT" , length = 1000)
    private String about ;
    @Column(name = "PASSWORD")
    private String password;

    // now every user has its rating's

    @Transient // we don't want to save this information in db so use Transient
    private List<Rating> ratingList = new ArrayList<>();



}
