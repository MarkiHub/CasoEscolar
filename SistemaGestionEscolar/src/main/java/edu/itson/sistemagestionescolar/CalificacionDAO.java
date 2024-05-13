/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.sistemagestionescolar;

import edu.itson.dominioescolar.Calificacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Elkur
 */
public class CalificacionDAO {

    private final String url = "jdbc:mysql://localhost:3306/sistemaescolar";
    private final String usuario = "root";
    private final String contraseña = "BaseDeDatos*";

    public Calificacion insert(Calificacion calificacion) throws SQLException {
        int idGenerado = 0;
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "INSERT INTO Calificaciones (calificacion, idAlumno, idCurso) VALUES (?, ?, ?)";
            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setFloat(1, calificacion.getCalificacion());
                statement.setLong(2, calificacion.getIdAlumno());
                statement.setLong(3, calificacion.getIdCurso());
                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idGenerado = generatedKeys.getInt(1);
                    calificacion.setId(idGenerado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return calificacion;
    }
}
