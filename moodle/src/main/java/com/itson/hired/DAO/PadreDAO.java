/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.DAO;

import edu.itson.dominioescolar.Padre;
import jakarta.servlet.ServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ildex
 */
@Repository
@AutoConfiguration
public class PadreDAO {

    private final String url = "jdbc:mysql://localhost:3306/sistemaescolar";
    private final String usuario = "root";
    private final String contraseña = "laresrangel";

    public PadreDAO() {
    }

    public List<Padre> obtenerPadresPorProfesor(Long idProfesor) {
        List<Padre> padres = new ArrayList<>();
        String query = "SELECT DISTINCT p.* "
                + "FROM Padres p "
                + "JOIN Alumnos a ON p.id = a.idPadre "
                + "JOIN AlumnosInscritos ai ON a.id = ai.idAlumno "
                + "JOIN Cursos c ON ai.idCurso = c.id "
                + "WHERE c.idProfesor = ?";
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, idProfesor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Padre padre = new Padre();
                    padre.setId(rs.getLong("id"));
                    padre.setNombreCompleto(rs.getString("nombreCompleto"));
                    padres.add(padre);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return padres;
    }

    public Long getIdPadreByIdAlumno(Long idAlumno) {
        Long idPadre = null;
        String query = "SELECT idPadre FROM Alumnos WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, idAlumno);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    idPadre = rs.getLong("idPadre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idPadre;
    }
    
    public String getNombreAlumnoById(long idAlumno) {
        String nombreAlumno = null;
        String query = "SELECT nombre FROM Alumnos WHERE id = ?";
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setLong(1, idAlumno);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nombreAlumno = rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreAlumno;
    }
}
