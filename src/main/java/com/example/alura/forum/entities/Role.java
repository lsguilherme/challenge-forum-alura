package com.example.alura.forum.entities;

import com.example.alura.forum.enums.RoleNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Enumerated
    @Column(unique = true)
    private RoleNameEnum name;
}
