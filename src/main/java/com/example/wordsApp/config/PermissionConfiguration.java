package com.example.wordsApp.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PermissionConfiguration {

    public String getUserPermission(){
        return "USER";
    }

    public String getAdminPermission(){
        return "ADMIN";
    }
}
