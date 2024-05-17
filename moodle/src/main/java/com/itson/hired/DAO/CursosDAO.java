/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.DAO;

import edu.itson.dominioescolar.Asignacion;
import edu.itson.dominioescolar.Curso;
import edu.itson.dominioescolar.DTO.CursoDTO;
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
public class CursosDAO {

    private final String url = "jdbc:mysql://localhost:3306/sistemaescolar";
    private final String usuario = "root";
    private final String contraseña = "BaseDeDatos*";

    public List<CursoDTO> getAllCursos(long idProfesor) {

        List<CursoDTO> cursos = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String sql = "SELECT C.id AS idMateria, M.nombre AS nombreMateria FROM Cursos C INNER JOIN Materias M ON M.id = C.idMateria WHERE C.idProfesor = ?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setLong(1, idProfesor);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CursoDTO C = new CursoDTO();
                C.setIdCurso(resultSet.getLong("idMateria"));
                C.setNombreMateria(resultSet.getString("nombreMateria"));
                cursos.add(C);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }
}
