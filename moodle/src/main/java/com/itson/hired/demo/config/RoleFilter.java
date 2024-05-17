/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.config;

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
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sr;
        HttpServletResponse res = (HttpServletResponse) sr1;

        Claims body = (Claims) req.getAttribute("MetaData");
        
        String rol = null;
        if (Validaciones.isProfesor(body)) {
            rol = String.valueOf(body.get("role"));
        } else if (Validaciones.isAlumno(body)) {
            rol = String.valueOf(body.get("role"));
        } else if (Validaciones.isPadre(body)) {
            rol = String.valueOf(body.get("role"));
        }
        if (rol != null) {
            req.setAttribute("rol", rol);
        }
        
        fc.doFilter(req, res);
    }
}
