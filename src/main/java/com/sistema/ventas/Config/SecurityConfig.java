package com.sistema.ventas.Config;


import com.sistema.ventas.Entities.Enums.Role;
import com.sistema.ventas.Services.UserInfoDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    //For authentication
    public UserDetailsService userDetailsService(){

        return new UserInfoDetailService();
    }


    @Bean
    //For authorization
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        //.requestMatchers("/api/ventas/createVenta").authenticated()
                        //.requestMatchers("/api/ventas/getVentas").hasAuthority(Role.CLIENTE.name())
                        .anyRequest().permitAll())
                .formLogin(form -> form.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
