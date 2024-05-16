/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Elkur
 */
public class DataFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        System.out.println(req.getRequestURI());
        if (req.getMethod().equalsIgnoreCase("get") && req.getRequestURI().equalsIgnoreCase("/ConsultarCursos")) {
            String token = req.getHeader("Authorization");
            
            Jws<Claims> claim = jwt.JwtGenerator.decodeJWT2(token);
            Claims body = claim.getBody();

            if (String.valueOf(body.get("role")).equalsIgnoreCase("Profesor")) {
                Long idProfesor = Long.valueOf(String.valueOf(body.get("userId")));
                req.setAttribute("idProfesor", idProfesor);
            } else {
                res.setStatus(401);
                return;
            }

        }

        filterChain.doFilter(req, res);
    }
}
