/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar.DAO;

import edu.itson.dominioescolar.Profesor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorDAO {

    // Establece la URL de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/nombre_base_datos";
    // Establece el nombre de usuario y contraseña de la base de datos
    private static final String USERNAME = "usuario";
    private static final String PASSWORD = "contraseña";

    // Consulta SQL para insertar un nuevo profesor en la base de datos
    private static final String INSERT_PROFESOR_SQL = "INSERT INTO profesores (id, nombre_completo) VALUES (?, ?)";
    // Consulta SQL para seleccionar un profesor por su ID
    private static final String SELECT_PROFESOR_BY_ID_SQL = "SELECT * FROM profesores WHERE id = ?";
    // Consulta SQL para actualizar el nombre completo de un profesor por su ID
    private static final String UPDATE_PROFESOR_SQL = "UPDATE profesores SET nombre_completo = ? WHERE id = ?";
    // Consulta SQL para eliminar un profesor por su ID
    private static final String DELETE_PROFESOR_SQL = "DELETE FROM profesores WHERE id = ?";

    public ProfesorDAO() {
    }

    // Método para insertar un nuevo profesor en la base de datos
    public void insertarProfesor(Profesor profesor) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFESOR_SQL)) {
            preparedStatement.setLong(1, profesor.getId());
            preparedStatement.setString(2, profesor.getNombreCompleto());
            preparedStatement.executeUpdate();
        }
    }

    // Método para obtener un profesor por su ID
    public Profesor obtenerProfesorPorId(long id) throws SQLException {
        Profesor profesor = null;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROFESOR_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                profesor = new Profesor();
                profesor.setId(resultSet.getLong("id"));
                profesor.setNombreCompleto(resultSet.getString("nombre_completo"));
            }
        }
        return profesor;
    }

    // Método para actualizar el nombre completo de un profesor por su ID
    public boolean actualizarProfesor(Profesor profesor) throws SQLException {
        boolean actualizado = false;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFESOR_SQL)) {
            preparedStatement.setString(1, profesor.getNombreCompleto());
            preparedStatement.setLong(2, profesor.getId());
            actualizado = preparedStatement.executeUpdate() > 0;
        }
        return actualizado;
    }

    // Método para eliminar un profesor por su ID
    public boolean eliminarProfesor(long id) throws SQLException {
        boolean eliminado = false;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROFESOR_SQL)) {
            preparedStatement.setLong(1, id);
            eliminado = preparedStatement.executeUpdate() > 0;
        }
        return eliminado;
    }
}
