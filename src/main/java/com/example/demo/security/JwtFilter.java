package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Excluir rutas públicas
        String path = request.getServletPath();

        if (
                path.startsWith("/api/auth/login") ||
                        path.startsWith("/api/auth/register") ||
                        path.startsWith("/api/auth/forgot") ||
                        path.startsWith("/api/auth/reset") ||
                        path.startsWith("/api/auth/refresh-token")
        ) {


            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader(("Authorization"));

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("You are not authorized. Invalid token or expired");
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("Invalid token or expired");
            return;

        }

        String email = jwtUtil.getEmailFromToken(token);
        String role = jwtUtil.getRoleFromToken(token);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null, List.of(() -> role));
        SecurityContextHolder.getContext().setAuthentication(authentication);



        filterChain.doFilter(request, response);

    }
}
