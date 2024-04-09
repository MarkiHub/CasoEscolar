/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.sistemagestionescolar;

import edu.itson.dominioescolar.Asignacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Elkur
 */
public class AsignacionDAO {

    private final String url = "jdbc:mysql://localhost:3306/sistemaescolar";
    private final String usuario = "root";
    private final String contraseña = "BaseDeDatos*";

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
