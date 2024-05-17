/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.config;

import com.itson.hired.demo.config.util.RolePages;
import com.itson.hired.demo.config.util.Validaciones;
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
public class PageAccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String rol = String.valueOf(req.getAttribute("rol"));
        String path = req.getServletPath();

        RolePages rp = Validaciones.getRolPages(rol);

        if (rp != null) {
            if (Validaciones.validateAccessPage(rp, path)) {
                filterChain.doFilter(req, res);
                return;
            }else{
                res.setStatus(403);
                return;
            }
        }

        filterChain.doFilter(req, res);
    }
}
