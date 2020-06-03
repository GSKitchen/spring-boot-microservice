package com.example.userauthservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userauthservice.dto.response.UserPermittedMenuResponse;
import com.example.userauthservice.entity.Menu;
import com.example.userauthservice.projection.UserPermittedMenu;
import com.example.userauthservice.repository.MenuRepository;
import com.example.userauthservice.repository.UserRepository;
import com.example.userauthservice.service.HomeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<UserPermittedMenuResponse> getPermittedMenu(int roleId) {
        List<Menu> menus = menuRepository.findAll();
        List<UserPermittedMenu> permittedMenus = userRepository.findMenuByRoleId(roleId);
        List<UserPermittedMenuResponse> list = new ArrayList<>();

        menus.stream().forEach(menu -> {
            UserPermittedMenuResponse dto = new UserPermittedMenuResponse();
            dto.setId(menu.getId());
            dto.setCode(menu.getCode());
            dto.setName(menu.getName());
            dto.setUrl(menu.getUrl());
            permittedMenus.stream().forEach(permit ->{
                if(permit.getId().equals(menu.getId())){
                    dto.getTypes().add(permit.getPermissionType());
                }
            });
            if(!dto.getTypes().isEmpty()){
                list.add(dto);
            }
        });

        return list;
    }
}
