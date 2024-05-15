/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.DAO;

import edu.itson.dominioescolar.Asignacion;
import edu.itson.dominioescolar.Calificacion;
import edu.itson.dominioescolar.DTO.EntregaDTO;
import edu.itson.dominioescolar.DTO.EntregasPadresDTO;
import edu.itson.dominioescolar.DTO.ReviewDTO;
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
                    + "EA.calificacion, EA.idAsignacion "
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
                        entrega.setIdAsignacion(resultSet.getLong("idAsignacion"));
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

    public Calificacion generarPromedio(long idAlumno, long idCurso) {
        int cantAsig = -1;
        int sum = -1;
        Calificacion calf = new Calificacion();
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query1 = "SELECT COUNT(A.id) AS cantAsig FROM Asignaciones A WHERE A.idCurso = ?;";
            String query2 = "SELECT SUM(EA.calificacion) AS sum "
                    + "FROM EntregaAsignacion EA "
                    + "INNER JOIN Asignaciones A ON A.id = EA.idAsignacion "
                    + "INNER JOIN Cursos C ON C.id = A.idCurso "
                    + "WHERE EA.idAlumno = ? AND C.id = ?;";
            try (PreparedStatement statement = con.prepareStatement(query1)) {
                statement.setLong(1, idCurso);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        cantAsig = resultSet.getInt("cantAsig");
                    }
                }
            }
            try (PreparedStatement statement = con.prepareStatement(query2)) {
                statement.setLong(1, idAlumno);
                statement.setLong(2, idCurso);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        sum = resultSet.getInt("sum");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        calf.setCalificacion(sum / cantAsig);
        calf.setIdAlumno(idAlumno);
        calf.setIdCurso(idCurso);
        return calf;
    }

    public List<ReviewDTO> getReviews(long idAlumno, long idAsignacion) {
        List<ReviewDTO> reviews = new ArrayList<>();
        long idCurso = getCursoFromAsignacion(idAsignacion);
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query
                    = "SELECT A.id AS idAsignacion, EA.idAlumno, EA.id AS idEntregaAsignacion, EA.calificacion FROM Asignaciones A "
                    + "INNER JOIN EntregaAsignacion EA ON EA.idAsignacion = A.id "
                    + "WHERE EA.idAlumno = ? AND A.idCurso = ?;";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setLong(1, idCurso);
                statement.setLong(2, idAlumno);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        ReviewDTO review = new ReviewDTO();
                        review.setIdAsignacion(resultSet.getLong("idAsignacion"));
                        review.setIdAlumno(resultSet.getLong("idAlumno"));
                        review.setIdEntregaAsignacion(resultSet.getLong("idEntregaAsignacion"));
                        review.setCalificacion(resultSet.getInt("calificacion"));
                        reviews.add(review);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public int getNumAsignaciones(long idAsignacion) {
        long id = -1;
        int numAsignaciones = -1;
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {

            String query2
                    = "SELECT COUNT(A.id) AS numAsignaciones FROM Asignaciones A WHERE A.idCurso = ?";
            id = getCursoFromAsignacion(idAsignacion);
            try (PreparedStatement statement = con.prepareStatement(query2)) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        numAsignaciones = resultSet.getInt("numAsignaciones");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numAsignaciones;
    }

    private long getCursoFromAsignacion(long idAsignacion) {
        long id = -1;
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query
                    = "SELECT C.id FROM Asignaciones A INNER JOIN Cursos C ON c.id = A.idCurso WHERE A.id = ?";

            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setLong(1, idAsignacion);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getLong("id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}
