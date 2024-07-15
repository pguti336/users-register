package com.challenge.users_register.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;


    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant modified;

    @Column(name = "last_login")
    private Instant lastLogin;

    @Column(columnDefinition = "boolean default true")
    private boolean active;

    private String token;

    public User() {
        this.active = true;
    }

    public User(UUID id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
