/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar.DAO;

import edu.itson.dominioescolar.Padre;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PadreDAO {

    // Establece la URL de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/nombre_base_datos";
    // Establece el nombre de usuario y contraseña de la base de datos
    private static final String USERNAME = "usuario";
    private static final String PASSWORD = "contraseña";

    // Consulta SQL para insertar un nuevo padre en la base de datos
    private static final String INSERT_PADRE_SQL = "INSERT INTO padres (id, nombre_completo) VALUES (?, ?)";
    // Consulta SQL para seleccionar un padre por su ID
    private static final String SELECT_PADRE_BY_ID_SQL = "SELECT * FROM padres WHERE id = ?";
    // Consulta SQL para actualizar el nombre completo de un padre por su ID
    private static final String UPDATE_PADRE_SQL = "UPDATE padres SET nombre_completo = ? WHERE id = ?";
    // Consulta SQL para eliminar un padre por su ID
    private static final String DELETE_PADRE_SQL = "DELETE FROM padres WHERE id = ?";

    public PadreDAO() {
    }

    // Método para insertar un nuevo padre en la base de datos
    public void insertarPadre(Padre padre) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PADRE_SQL)) {
            preparedStatement.setLong(1, padre.getId());
            preparedStatement.setString(2, padre.getNombreCompleto());
            preparedStatement.executeUpdate();
        }
    }

    // Método para obtener un padre por su ID
    public Padre obtenerPadrePorId(long id) throws SQLException {
        Padre padre = null;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PADRE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                padre = new Padre();
                padre.setId(resultSet.getLong("id"));
                padre.setNombreCompleto(resultSet.getString("nombre_completo"));
            }
        }
        return padre;
    }

    // Método para actualizar el nombre completo de un padre por su ID
    public boolean actualizarPadre(Padre padre) throws SQLException {
        boolean actualizado = false;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PADRE_SQL)) {
            preparedStatement.setString(1, padre.getNombreCompleto());
            preparedStatement.setLong(2, padre.getId());
            actualizado = preparedStatement.executeUpdate() > 0;
        }
        return actualizado;
    }

    // Método para eliminar un padre por su ID
    public boolean eliminarPadre(long id) throws SQLException {
        boolean eliminado = false;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PADRE_SQL)) {
            preparedStatement.setLong(1, id);
            eliminado = preparedStatement.executeUpdate() > 0;
        }
        return eliminado;
    }
}
