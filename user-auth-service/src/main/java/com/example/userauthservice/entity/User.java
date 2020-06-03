package com.example.userauthservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private boolean active;

    //private String roles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role roles;

    private boolean expired;

    private boolean locked;

    private String allowedSurah;

    public User() {
    }

    public User(int id, String username, String password, boolean active, Role roles, boolean expired, boolean locked, String allowedSurah) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.expired = expired;
        this.locked = locked;
        this.allowedSurah = allowedSurah;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getAllowedSurah() {
        return allowedSurah;
    }

    public void setAllowedSurah(String allowedSurah) {
        this.allowedSurah = allowedSurah;
    }
}
