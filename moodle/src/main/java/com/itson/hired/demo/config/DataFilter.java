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
public class DataFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        Claims body = (Claims) req.getAttribute("MetaData");

        if (Validaciones.isProfesor(body)) {
            Long idProfesor = Long.valueOf(String.valueOf(body.get("userId")));
            req.setAttribute("idProfesor", idProfesor);
        } else if (Validaciones.isAlumno(body)) {
            Long idAlumno = Long.valueOf(String.valueOf(body.get("userId")));
            req.setAttribute("idAlumno", idAlumno);
        } else if (Validaciones.isPadre(body)) {
            Long idPadre = Long.valueOf(String.valueOf(body.get("userId")));
            req.setAttribute("idPadre", idPadre);
        }

        filterChain.doFilter(req, res);
    }
}
