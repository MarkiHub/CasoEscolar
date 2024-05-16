/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

/**
 *
 * @author Elkur
 */
//Implementation layer to implement Filter 
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.POST})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        if (req.getHeader("Authorization") == null && !req.getServletPath().equalsIgnoreCase("/nigga") && !req.getServletPath().equalsIgnoreCase("/dogo")) {
            res.setStatus(401);
            return;
        }
        if (req.getMethod().equalsIgnoreCase("post") && !req.getServletPath().equalsIgnoreCase("/nigga") && !req.getServletPath().equalsIgnoreCase("/dogo")) {
            String token = req.getHeader("Authorization");
            System.out.println(token);

            Jws<Claims> claim = jwt.JwtGenerator.decodeJWT2(token);
            if (claim == null) {
                res.setStatus(401);
                return;
            }

        }

        filterChain.doFilter(req, res);
    }
}