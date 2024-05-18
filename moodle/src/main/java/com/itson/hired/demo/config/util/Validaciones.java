/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.config.util;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Elkur
 */
public class Validaciones {

    public static boolean isProfesor(Claims clm) {
        if (clm == null) {
            return false;
        }

        if (clm.get("role") == null) {
            return false;
        }
        return String.valueOf(clm.get("role")).equalsIgnoreCase("Profesor");
    }

    public static boolean isAlumno(Claims clm) {
        if (clm == null) {
            return false;
        }
        if (clm.get("role") == null) {
            return false;
        }
        return String.valueOf(clm.get("role")).equalsIgnoreCase("Alumno");
    }

    public static boolean isPadre(Claims clm) {
        if (clm == null) {
            return false;
        }
        if (clm.get("role") == null) {
            return false;
        }
        return String.valueOf(clm.get("role")).equalsIgnoreCase("Padre");
    }

    public static boolean validateOpenPaths(String path) {
        boolean isOpenPath = false;
        List<OpenPaths> pathsAbiertos = Arrays.asList(
                OpenPaths.TokenAccess,
                OpenPaths.TokenCreation,
                OpenPaths.TemporalIndex1,
                OpenPaths.TemporalIndex2,
                OpenPaths.index
        );

        for (OpenPaths op : pathsAbiertos) {
            if (path.equalsIgnoreCase(op.getPath())) {
                isOpenPath = true;
            }
        }
        return isOpenPath;
    }

    public static RolePages getRolPages(String rol) {
        RolePages rolF = null;

        List<RolePages> paginasPorRol = Arrays.asList(
                RolePages.alumno,
                RolePages.padre,
                RolePages.profesor
        );

        if (rol == null) {
            return null;
        }

        for (RolePages rp : paginasPorRol) {
            if (rol.equalsIgnoreCase(rp.getRol())) {
                rolF = rp;
            }
        }
        return rolF;
    }

    public static boolean validateAccessPage(RolePages rp, String path) {
        boolean valido = false;
        for (String pathR : rp.getPathsAccess()) {
            if (path.equalsIgnoreCase(pathR)) {
                valido = true;
            }
        }
        return valido;
    }
}
