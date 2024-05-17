/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.DAO;

import com.itson.hired.interfaces.IMensajeDAO;
import edu.itson.dominioescolar.Conversacion;
import edu.itson.dominioescolar.Mensaje;
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
public class MensajeDAO implements IMensajeDAO {

    private final String url = "jdbc:mysql://localhost:3306/sistemaescolar";
    private final String usuario = "root";
    private final String contraseña = "laresrangel";

    public MensajeDAO() {
    }

    @Override
    public void insert(Mensaje mensaje) {
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "INSERT INTO Mensajes (id_conversacion, id_sender, texto) VALUES (?, ?, ?)";
            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, mensaje.getConversacion().getId());
                statement.setLong(2, mensaje.getIdSender());
                statement.setString(3, mensaje.getText());
                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    mensaje.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Mensaje> getMensajesPorConversacion(Long idConversacion) {
        List<Mensaje> mensajes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "SELECT * FROM Mensajes WHERE id_conversacion = ?";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setLong(1, idConversacion);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Mensaje mensaje = new Mensaje();
                        mensaje.setId(resultSet.getLong("id"));
                        mensaje.setIdSender(resultSet.getLong("id_sender"));
                        mensaje.setText(resultSet.getString("texto"));
                        mensaje.setFechaEnviado(resultSet.getDate("fechaEnviado"));
                        mensajes.add(mensaje);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensajes;
    }

    @Override
    public Mensaje getMensajePorId(Long idMensaje) {
        Mensaje mensaje = null;
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "SELECT * FROM Mensajes WHERE id = ?";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setLong(1, idMensaje);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        mensaje = new Mensaje();
                        mensaje.setId(resultSet.getLong("id"));
                        mensaje.setIdSender(resultSet.getLong("id_sender"));
                        mensaje.setText(resultSet.getString("texto"));
                        mensaje.setFechaEnviado(resultSet.getDate("fechaEnviado"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public void crearConversacion(Conversacion conversacion) {
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "INSERT INTO Conversaciones (id_profesor, id_padre) VALUES (?, ?)";
            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, conversacion.getProfesor().getId());
                statement.setLong(2, conversacion.getPadre().getId());
                statement.executeUpdate();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    conversacion.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMensaje(Long idMensaje) {
        try (Connection con = DriverManager.getConnection(url, usuario, contraseña)) {
            String query = "DELETE FROM Mensajes WHERE id = ?";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setLong(1, idMensaje);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
