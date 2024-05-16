/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar.DAO;

import edu.itson.dominioescolar.Alumno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAO {

    // Establece la URL de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/nombre_base_datos";
    // Establece el nombre de usuario y contraseña de la base de datos
    private static final String USERNAME = "usuario";
    private static final String PASSWORD = "contraseña";

    // Consulta SQL para insertar un nuevo alumno en la base de datos
    private static final String INSERT_ALUMNO_SQL = "INSERT INTO alumnos (id, nombre_completo) VALUES (?, ?)";
    // Consulta SQL para seleccionar un alumno por su ID
    private static final String SELECT_ALUMNO_BY_ID_SQL = "SELECT * FROM alumnos WHERE id = ?";
    // Consulta SQL para actualizar el nombre completo de un alumno por su ID
    private static final String UPDATE_ALUMNO_SQL = "UPDATE alumnos SET nombre_completo = ? WHERE id = ?";
    // Consulta SQL para eliminar un alumno por su ID
    private static final String DELETE_ALUMNO_SQL = "DELETE FROM alumnos WHERE id = ?";

    public AlumnoDAO() {
    }

    // Método para insertar un nuevo alumno en la base de datos
    public void insertarAlumno(Alumno alumno) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ALUMNO_SQL)) {
            preparedStatement.setLong(1, alumno.getId());
            preparedStatement.setString(2, alumno.getNombreCompleto());
            preparedStatement.executeUpdate();
        }
    }

    // Método para obtener un alumno por su ID
    public Alumno obtenerAlumnoPorId(long id) throws SQLException {
        Alumno alumno = null;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALUMNO_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                alumno = new Alumno();
                alumno.setId(resultSet.getLong("id"));
                alumno.setNombreCompleto(resultSet.getString("nombre_completo"));
            }
        }
        return alumno;
    }

    // Método para actualizar el nombre completo de un alumno por su ID
    public boolean actualizarAlumno(Alumno alumno) throws SQLException {
        boolean actualizado = false;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ALUMNO_SQL)) {
            preparedStatement.setString(1, alumno.getNombreCompleto());
            preparedStatement.setLong(2, alumno.getId());
            actualizado = preparedStatement.executeUpdate() > 0;
        }
        return actualizado;
    }

    // Método para eliminar un alumno por su ID
    public boolean eliminarAlumno(long id) throws SQLException {
        boolean eliminado = false;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALUMNO_SQL)) {
            preparedStatement.setLong(1, id);
            eliminado = preparedStatement.executeUpdate() > 0;
        }
        return eliminado;
    }
}
