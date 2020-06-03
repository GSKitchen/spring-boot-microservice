package com.example.userauthservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.userauthservice.entity.User;
import com.example.userauthservice.projection.UserPermittedMenu;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT new com.example.userauthservice.projection.UserPermittedMenu(" +
            " m.id, m.name, pt.code, m.url, m.code" +
            " ) FROM Permissions p" +
            " JOIN p.menu m" +
            " JOIN p.permissionType pt" +
            " WHERE p.role.id = ?1")
    public List<UserPermittedMenu> findMenuByRoleId(int roleId);
}
