/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.DAO;

import edu.itson.dominioescolar.Asignacion;
import edu.itson.dominioescolar.EntregaAsignacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ildex
 */
public class AsignacionDAO {

    private final String url = "jdbc:mysql://localhost:3306/sistemaescolar";
    private final String usuario = "root";
    private final String contraseña = "laresrangel";

    public void insert(Asignacion asignacion) throws SQLException {
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "INSERT INTO Asignaciones (nombre, idCurso) VALUES (?, ?)";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setString(1, asignacion.getNombre());
                statement.setLong(2, asignacion.getIdCurso());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void enviarEntrega(EntregaAsignacion entrega) throws SQLException {
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "INSERT INTO entregaAsignacion (calificacion, idAlumno, idAsignacion) VALUES (?, ?, ?)";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setFloat(1, entrega.getCalificacion());
                statement.setLong(2, entrega.getIdAlumno());
                statement.setLong(3, entrega.getIdAsignacion());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Asignacion get(long id) {
        Asignacion asignacion = null;

        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "SELECT * FROM Asignaciones WHERE id = ?";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        asignacion = new Asignacion();
                        asignacion.setId(resultSet.getLong("id"));
                        asignacion.setNombre(resultSet.getString("nombre"));
                        asignacion.setIdCurso(resultSet.getLong("idCurso"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asignacion;
    }
}
