/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.DAO;

import edu.itson.dominioescolar.Profesor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ildex
 */
@Repository
@AutoConfiguration
public class ProfesorDAO {

    private final String url = "jdbc:mysql://localhost:3306/sistemaescolar";
    private final String usuario = "root";
    private final String contraseña = "laresrangel";

    public ProfesorDAO() {
    }

    public Profesor obtenerProfesorPorId(Long idProfesor) {
        Profesor profesor = null;
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "SELECT * FROM Profesores WHERE id = ?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setLong(1, idProfesor);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        profesor = new Profesor();
                        profesor.setId(rs.getLong("id"));
                        profesor.setNombreCompleto(rs.getString("nombreCompleto"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesor;
    }
}
