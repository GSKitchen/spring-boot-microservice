package com.example.userauthservice.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private int id;
    private String username;
    private String password;
    private boolean active;
    private boolean expired;
    private boolean locked;
    private List<GrantedAuthority> authorities;
    private Set<Integer> allowedSurah = new HashSet<>();

    public MyUserDetails() {
    }

    public MyUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.expired = user.isExpired();
        this.locked = user.isLocked();
        //this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        //this.authorities = Arrays.stream(user.getRoles().getCode().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        this.authorities = getAuthorities(user.getRoles());
        this.allowedSurah = Arrays.stream(user.getAllowedSurah().split(",")).map(Integer::new).collect(Collectors.toSet());
    }

    private List<GrantedAuthority> getAuthorities(Role role) {
        return getAuth(role, getPriviledges(role));
    }

    private List<String> getPriviledges(Role role){
        return role.getPermissions().stream().map(permission -> permission.getAuthority()).collect(Collectors.toList());
    }

    private List<GrantedAuthority> getAuth(Role role, List<String> permissions){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getCode()));
        permissions.stream().map(permission -> authorities.add(new SimpleGrantedAuthority(permission))).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public Set<Integer> getAllowedSurah() {
        return allowedSurah;
    }

    public void setAllowedSurah(Set<Integer> allowedSurah) {
        this.allowedSurah = allowedSurah;
    }
}
