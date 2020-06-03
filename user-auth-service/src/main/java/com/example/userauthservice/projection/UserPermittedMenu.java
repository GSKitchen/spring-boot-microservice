package com.example.userauthservice.projection;

import lombok.Value;

@Value
public class UserPermittedMenu {

    private Short id;
    private String name;
    private String permissionType;
    private String url;
    private String code;
}
