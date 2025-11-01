package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter, CustomAccessDeniedHandler accessDeniedHandler) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults()) // mantiene tu configuración actual de CORS
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // Endpoints públicos sin token
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/forgot").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/reset").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/refresh").permitAll()


                        // Endpoints del cliente que requieren autenticación
                        // Ambos endpoints my-profile y edit-profile deben estar protegidos
                        .requestMatchers("/api/customer/my-profile", "/api/customer/edit-profile").authenticated()

                        // Cambiar contraseña requiere estar logueado
                        .requestMatchers(HttpMethod.POST, "/api/auth/change-password").authenticated()

                        // Productos
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/products/*/reactivate").hasAuthority("ADMIN")


                        //Categorias
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/categories/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasAuthority("ADMIN")

                        //Mercado Pago
                        .requestMatchers(HttpMethod.POST, "/api/payments/create-preference").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/payments/process-payment").authenticated()





                        // Todo lo demás requiere autenticación
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }




    // Configuración global de CORS para toda la app
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Para todas las rutas
                        .allowedOrigins("http://localhost:5173") // Origen de tu frontend
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // Métodos permitidos
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}
