/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.DAO;

import edu.itson.dominioescolar.Asignacion;
import edu.itson.dominioescolar.DTO.EntregaDTO;
import edu.itson.dominioescolar.DTO.EntregasPadresDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elkur
 */
public class EntregasDAO {

    private final String url = "jdbc:mysql://localhost:3306/sistemaescolar";
    private final String usuario = "root";
    private final String contraseña = "BaseDeDatos*";

    public List<EntregasPadresDTO> getAsignacionesAprobar(Long idPadre) {
        List<EntregasPadresDTO> asigAprobar = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query
                    = "SELECT A.id AS idAsignacion, EA.fechaEntrega, EA.id AS idEntrega, ASI.nombre AS nombreAsignacion, A.nombreCompleto AS nombreAlumno "
                    + "FROM Alumnos A "
                    + "INNER JOIN EntregaAsignacion EA ON A.id = EA.idAlumno "
                    + "INNER JOIN Asignaciones ASI ON ASI.id = EA.idAsignacion "
                    + "WHERE A.idPadre = ? AND EA.aprobada = FALSE;";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setLong(1, idPadre);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        EntregasPadresDTO ep = new EntregasPadresDTO();
                        ep.setIdAsignacion(resultSet.getLong("idAsignacion"));
                        ep.setFechaEntrega(resultSet.getTimestamp("fechaEntrega"));
                        ep.setIdEntrega(resultSet.getLong("idEntrega"));
                        ep.setNombreAsignacion(resultSet.getString("nombreAsignacion"));
                        ep.setNombreAlumno(resultSet.getString("nombreAlumno"));
                        asigAprobar.add(ep);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asigAprobar;
    }

    public void aprobarAsignacion(EntregasPadresDTO entrega) {
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query
                    = "UPDATE EntregaAsignacion EA SET EA.aprobada = TRUE WHERE EA.id = ? ;";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setLong(1, entrega.getIdEntrega());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EntregaDTO> getEntregasPendientes(long idAsignacion) {
        List<EntregaDTO> entrePendientes = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query
                    = "SELECT EA.id AS idEntrega, A.nombre AS nombreAsignacion, "
                    + "EA.fechaEntrega, AL.id AS idAlumno, AL.nombreCompleto AS nombreAlumno, "
                    + "EA.calificacion "
                    + "FROM EntregaAsignacion EA "
                    + "INNER JOIN Asignaciones A ON EA.idAsignacion = A.id "
                    + "INNER JOIN Alumnos AL ON AL.id = EA.idAlumno "
                    + "WHERE EA.calificacion IS NULL AND A.id = ? AND EA.aprobada = TRUE;";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setLong(1, idAsignacion);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        EntregaDTO entrega = new EntregaDTO();
                        entrega.setFechaEntrega(resultSet.getTimestamp("fechaEntrega"));
                        entrega.setIdAlumno(resultSet.getLong("idAlumno"));
                        entrega.setIdEntrega(resultSet.getLong("idEntrega"));
                        entrega.setNombreAlumno(resultSet.getString("nombreAlumno"));
                        entrega.setNombreAsignacion(resultSet.getString("nombreAsignacion"));
                        entrePendientes.add(entrega);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrePendientes;
    }

    public void asignarCalificacion(EntregaDTO entregaDTO) {
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query
                    = "UPDATE EntregaAsignacion EA SET EA.calificacion = ? WHERE EA.id = ? ;";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setInt(1, entregaDTO.getCalificacion());
                statement.setLong(2, entregaDTO.getIdEntrega());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}