package com.example.wordsApp.security.config;

import com.example.wordsApp.config.PermissionConfiguration;
import com.example.wordsApp.config.SecurityPaths;
import com.example.wordsApp.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private AuthenticationService authenticationService;
    private PasswordConfig passwordConfig;
    private AuthenticationConfiguration authenticationConfiguration;

    private SecurityPaths securityPaths;
    private PermissionConfiguration permissionConfiguration;

    public SecurityConfig(AuthenticationService authenticationService, PasswordConfig passwordConfig,
                          AuthenticationConfiguration authenticationConfiguration, SecurityPaths securityPaths,
                          PermissionConfiguration permissionConfiguration) {
        this.authenticationService = authenticationService;
        this.passwordConfig = passwordConfig;
        this.authenticationConfiguration = authenticationConfiguration;
        this.securityPaths = securityPaths;
        this.permissionConfiguration = permissionConfiguration;
    }

    @Autowired



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                .csrf()
                .disable()

                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, securityPaths.getPublicPermissionToGet())
                .permitAll().and()

                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, securityPaths.getPublicPermissionToPost())
                .permitAll().and()


                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, securityPaths.getUserPermissionToGet())
                //.hasAuthority("USER").and()
                .hasAuthority(permissionConfiguration.getUserPermission()).and()

                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, securityPaths.getUserPermissionToPost())
                .hasAuthority(permissionConfiguration.getUserPermission()).and()

                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.PUT, securityPaths.getUserPermissionToPut())
                .hasAuthority(permissionConfiguration.getUserPermission()).and()

                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.DELETE, securityPaths.getUserPermissionToDelete())
                .hasAuthority(permissionConfiguration.getUserPermission()).and()


                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, securityPaths.getAdminPermissionToGet())
                .hasAuthority(permissionConfiguration.getAdminPermission()).and()

                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, securityPaths.getAdminPermissionToPost())
                .hasAuthority(permissionConfiguration.getAdminPermission()).and()

                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.PUT, securityPaths.getAdminPermissionToPut())
                .hasAuthority(permissionConfiguration.getAdminPermission()).and()

                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.DELETE, securityPaths.getAdminPermissionToDelete())
                .hasAuthority(permissionConfiguration.getAdminPermission()).and()


                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, securityPaths.getUserOrAdminPermissionToGet())
                .hasAnyAuthority(permissionConfiguration.getUserPermission(),
                                 permissionConfiguration.getAdminPermission()).and()


                //access for everyone

//                .authorizeHttpRequests()
//                .requestMatchers("**")
//                //.requestMatchers("/users/sign-up")
//                .permitAll()
//                .requestMatchers("/all")
//                .permitAll()
//                .and()

                //access for ADMIN

//                .authorizeHttpRequests()
//                .requestMatchers("/users")
//                .hasAuthority("ADMIN")
//                .and()

                //access after logging in for everyone

                .authorizeHttpRequests()
                //.requestMatchers("/ok")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                //.httpBasic()
                .and()
                .build();

    }

//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }


}