/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.DAO;

import com.itson.hired.interfaces.IConversacionDAO;
import edu.itson.dominioescolar.Conversacion;
import edu.itson.dominioescolar.Padre;
import edu.itson.dominioescolar.Profesor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ildex
 */
@Repository
@AutoConfiguration
public class ConversacionDAO implements IConversacionDAO {

    private final String url = "jdbc:mysql://localhost:3306/sistemaescolar";
    private final String usuario = "root";
    private final String contraseña = "laresrangel";

    @Override
    public Conversacion crearConversacion(Conversacion conversacion) {
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "INSERT INTO Conversaciones (id_profesor, id_padre) VALUES (?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setLong(1, conversacion.getProfesor().getId());
                stmt.setLong(2, conversacion.getPadre().getId());
                stmt.executeUpdate();

                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    conversacion.setId(generatedKeys.getLong(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conversacion;
    }

    @Override
    public Optional<Conversacion> obtenerConversacionPorProfesorYPadre(Long idProfesor, Long idPadre) {
        Conversacion conversacion = null;
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "SELECT * FROM Conversaciones WHERE id_profesor = ? AND id_padre = ?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setLong(1, idProfesor);
                stmt.setLong(2, idPadre);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    conversacion = new Conversacion();
                    conversacion.setId(rs.getLong("id"));
                    Profesor profesor = new Profesor();
                    profesor.setId(rs.getLong("id_profesor"));
                    conversacion.setProfesor(profesor);
                    Padre padre = new Padre();
                    padre.setId(rs.getLong("id_padre"));
                    conversacion.setPadre(padre);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(conversacion);
    }
}
