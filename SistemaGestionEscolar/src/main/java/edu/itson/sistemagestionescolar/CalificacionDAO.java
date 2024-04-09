/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.sistemagestionescolar;

import edu.itson.dominioescolar.Calificacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Elkur
 */
public class CalificacionDAO {

    private final String url = "jdbc:mysql://localhost:3306/sistemaescolar";
    private final String usuario = "root";
    private final String contraseña = "laresrangel";

    public void insert(Calificacion calificacion) throws SQLException {
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "INSERT INTO Calificaciones (calificacion, idAlumno, idAsignacion) VALUES (?, ?, ?)";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setFloat(1, calificacion.getCalificacion());
                statement.setLong(2, calificacion.getIdAlumno());
                statement.setLong(3, calificacion.getIdAsignacion());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
