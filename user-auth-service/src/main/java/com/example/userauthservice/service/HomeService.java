package com.example.userauthservice.service;

import java.util.List;

import com.example.userauthservice.dto.response.UserPermittedMenuResponse;

public interface HomeService {

    public List<UserPermittedMenuResponse> getPermittedMenu(int roleId);
}
