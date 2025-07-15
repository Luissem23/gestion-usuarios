package com.luis.gestionusuarios.config;

import com.luis.gestionusuarios.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SeguridadConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //  Se incorporó este método para definir el codificador de contraseñas como un bean
    // Esto permite que Spring gestione el objeto y mejora la reutilización
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Se refactorizó este bloque extrayéndolo como un método independiente con @Bean
    // Mejora la claridad del código y sigue el principio de responsabilidad única
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                //  Se organizó esta sección para dejar claras las rutas públicas
                .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                //  Se definió explícitamente el rol necesario para acceder a /trabajadores/**
                .requestMatchers("/trabajadores/**").hasRole("SUPERVISOR")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // Se personalizó la página de login
                .loginPage("/login")
                //  Se configuró la redirección al inicio tras iniciar sesión
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                //  Se configuró la ruta de logout y limpieza de sesión
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }
}
