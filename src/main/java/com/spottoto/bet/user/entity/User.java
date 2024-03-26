package com.spottoto.bet.user.entity;

import com.spottoto.bet.account.controller.request.RegisterRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateDt;
    private String firstname;
    private String lastname;
    private String password;
    private String mail;
    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.ROLE_USER;

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public User create(RegisterRequest request) {
        firstname = request.getFirstname();
        lastname = request.getLastname();
        setPassword(password = request.getPassword());
        mail = request.getMail();
        createDt = LocalDateTime.now();
        return this;
    }

    @PreUpdate
    public void onUpdate() {
        updateDt = LocalDateTime.now();
    }
}
