/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.itson.hired.demo.config.util;

/**
 *
 * @author Elkur
 */
public enum OpenPaths {
    TokenCreation("/nigga"), TokenAccess("/dogo"), TemporalIndex1("/profesor/Cursos"), TemporalIndex2("/profesor/Chat"), index("/");
    private final String path;

    private OpenPaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
