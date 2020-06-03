package com.example.userauthservice.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserPermittedMenuResponse {
    private Short id;
    private String name;
    private List<String> types = new ArrayList<>();
    private String url;
    private String code;
}
