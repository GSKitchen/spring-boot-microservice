package com.example.userauthservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userauthservice.entity.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Short> {

}
