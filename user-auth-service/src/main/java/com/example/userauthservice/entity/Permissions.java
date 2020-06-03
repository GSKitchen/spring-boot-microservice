package com.example.userauthservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_permission")
@Data
@NoArgsConstructor
public class Permissions implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToOne
    @JoinColumn(name = "permission_type_id")
    private PermissionType permissionType;

    @Override
    public String getAuthority() {
        return menu.getCode() + "_" + permissionType.getCode();
    }
}
