/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.DAO;

import edu.itson.dominioescolar.Asignacion;
import edu.itson.dominioescolar.DTO.EntregaDTO;
import edu.itson.dominioescolar.EntregaAsignacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ildex
 */
public class AsignacionDAO {

    private final String url = "jdbc:mysql://localhost:3306/sistemaescolar";
    private final String usuario = "root";
    private final String contraseña = "BaseDeDatos*";

    public void insert(Asignacion asignacion) throws SQLException {
        int idGenerado;
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "INSERT INTO Asignaciones (nombre, idCurso) VALUES (?, ?)";
            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, asignacion.getNombre());
                statement.setLong(2, asignacion.getIdCurso());
                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idGenerado = generatedKeys.getInt(1);
                    asignacion.setId(idGenerado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void enviarEntrega(EntregaAsignacion entrega) throws SQLException {
        int idGenerado;
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "INSERT INTO entregaAsignacion (idAlumno, idAsignacion, fechaEntrega) VALUES (?, ?, ?)";
            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, entrega.getIdAlumno());
                statement.setLong(2, entrega.getIdAsignacion());
                Timestamp fechaEntrega = new Timestamp(new java.util.Date().getTime());
                entrega.setFechaEntrega(fechaEntrega);
                statement.setTimestamp(3, fechaEntrega);
                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idGenerado = generatedKeys.getInt(1);
                    entrega.setId(idGenerado);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Asignacion> getAllCurso(long idCurso, long idAlumno) {
        Asignacion asignacion = null;
        List<Asignacion> asignacionesP = new ArrayList<>();
        
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "SELECT A.* FROM AlumnosInscritos AI "
                    + "INNER JOIN Asignaciones A ON AI.idCurso = A.idCurso "
                    + "LEFT JOIN EntregaAsignacion EA ON EA.idAsignacion = A.id "
                    + "WHERE AI.idAlumno = ? AND A.idCurso = ? AND EA.id IS NULL;";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setLong(1, idAlumno);
                statement.setLong(2, idCurso);
                try (ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        asignacion = new Asignacion();
                        asignacion.setId(resultSet.getLong("id"));
                        asignacion.setNombre(resultSet.getString("nombre"));
                        asignacion.setIdCurso(resultSet.getLong("idCurso"));
                        asignacionesP.add(asignacion);
                    }
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asignacionesP;
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
