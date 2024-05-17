/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.config;

import com.itson.hired.demo.config.util.OpenPaths;
import com.itson.hired.demo.config.util.Validaciones;
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
import java.util.Arrays;
import java.util.List;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String token = req.getHeader("Authorization");
        String method = req.getMethod();
        String path = req.getServletPath();
        Jws<Claims> claim = null;

        boolean isOpenPath = Validaciones.validateOpenPaths(path);

        if (isOpenPath) {
            filterChain.doFilter(req, res);
            return;
        }

        if (method.equalsIgnoreCase("get") && token == null) {
            filterChain.doFilter(req, res);
            return;
        }

        if (token == null) {
            res.setStatus(401);
            return;
        } else {
            claim = jwt.JwtGenerator.decodeJWT2(token);
            req.setAttribute("MetaData", claim.getBody());
        }

        if (method.equalsIgnoreCase("post")) {
            claim = jwt.JwtGenerator.decodeJWT2(token);
            if (claim == null) {
                System.out.println("Token invalido");
                res.setStatus(401);
                return;
            } else {
                req.setAttribute("MetaData", claim.getBody());
            }
        }

        filterChain.doFilter(req, res);
    }
}
