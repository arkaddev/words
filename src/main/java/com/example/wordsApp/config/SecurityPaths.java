package com.example.wordsApp.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityPaths {

    private static final String ALL = "/all";
    private static final String USERS = "/users";
    private static final String SIGN_UP = "/sign-up";
    private static final String ID = "/{id}";


    public String[] getPublicPermissionToGet(){
        return new String[]{ALL};
    }

    public String[] getPublicPermissionToPost(){
        return new String[]{USERS + SIGN_UP};
    }

    public String[] getUserPermissionToGet(){
        return new String[]{};
    }

    public String[] getUserPermissionToPost(){
        return new String[]{};
    }

    public String[] getUserPermissionToPut(){
        return new String[]{USERS + ID};
    }

    public String[] getUserPermissionToDelete(){
        return new String[]{USERS + ID};
    }

    public String[] getAdminPermissionToGet(){
        return new String[]{USERS};
    }

    public String[] getAdminPermissionToPost(){
        return new String[]{};
    }

    public String[] getAdminPermissionToPut(){
        return new String[]{USERS + ID};
    }

    public String[] getAdminPermissionToDelete(){
        return new String[]{USERS + ID};
    }

    public String[] getUserOrAdminPermissionToGet(){
        return new String[]{};
    }
}
