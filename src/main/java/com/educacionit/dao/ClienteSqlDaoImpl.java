package com.educacionit.dao;

import com.educacionit.config.DatabaseConnection;
import com.educacionit.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteSqlDaoImpl implements ClienteDao {

  private static final String INSERT_QUERY = "INSERT INTO clientes(nombre, apellido, email, fecha_creacion) VALUES(?, ?, ?, ?)";
  private static final String UPDATE_QUERY = "UPDATE clientes SET nombre=?, apellido=?, email=?, fecha_creacion=? WHERE id = ?";
  private static final String SELECT_BY_ID_QUERY = "SELECT id, nombre, apellido, email, fecha_creacion FROM clientes WHERE id = ?";
  private static final String SELECT_ALL_QUERY = "SELECT id, nombre, apellido, email, fecha_creacion FROM clientes";
  private static final String DELETE_BY_ID_QUERY = "DELETE FROM clientes WHERE id = ?";

  public void insert(Cliente cliente) {
    Connection connection = null;

    try {
      // Obtengo conexion de DB
      connection = DatabaseConnection.getConnection();
      // Obtengo objeto para construir query
      PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY);

      // Agrego parametros query
      stmt.setString(1, cliente.getNombre());
      stmt.setString(2, cliente.getApellido());
      stmt.setString(3, cliente.getEmail());
      stmt.setDate(4, new Date(cliente.getFechaCreacion().getTime()));

      // Ejecuto Query
      stmt.execute();
    } catch (SQLException ex) {
      ex.printStackTrace(System.out);
    } finally {

      if (connection != null) {
        DatabaseConnection.close(connection);
      }
    }
  }

  public void update(Cliente Cliente) {
    Connection connection = null;

    try {
      connection = DatabaseConnection.getConnection();

      PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
      preparedStatement.setString(1, Cliente.getNombre());
      preparedStatement.setString(2, Cliente.getApellido());
      preparedStatement.setString(3, Cliente.getEmail());
      preparedStatement.setDate(4, new Date(Cliente.getFechaCreacion().getTime()));
      preparedStatement.setLong(5, Cliente.getId());

      int registrosAfectados = preparedStatement.executeUpdate();
      System.out.println("registros afectados " + registrosAfectados);

    } catch (SQLException ex) {
      ex.printStackTrace(System.out);
    } finally {
      if (connection != null) {
        DatabaseConnection.close(connection);
      }
    }
  }

  public Cliente getById(Long id) {
    Connection connection = null;
    Cliente cliente = null;

    try {
      connection = DatabaseConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();

      resultSet.next();
      cliente = new Cliente();
      cliente.setId(resultSet.getLong("id"));
      cliente.setFechaCreacion(resultSet.getDate("fecha_creacion"));
      cliente.setNombre(resultSet.getString("nombre"));
      cliente.setApellido(resultSet.getString("apellido"));
      cliente.setEmail(resultSet.getString("email"));

    } catch (SQLException ex) {
      ex.printStackTrace(System.out);
    } finally {
      if (connection != null) {
        DatabaseConnection.close(connection);
      }
    }
    return cliente;
  }

  public List<Cliente> getClientes() {
    Connection connection = null;
    List<Cliente> clientes = new ArrayList();

    try {
      connection = DatabaseConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getLong("id"));
        cliente.setFechaCreacion(resultSet.getDate("fecha_creacion"));
        cliente.setNombre(resultSet.getString("nombre"));
        cliente.setApellido(resultSet.getString("apellido"));
        cliente.setEmail(resultSet.getString("email"));

        clientes.add(cliente);

      }
    } catch (SQLException ex) {
      ex.printStackTrace(System.out);
    } finally {
      if (connection != null) {
        DatabaseConnection.close(connection);
      }
    }
    return clientes;
  }

  public void delete(Long id) {
    Connection connection = null;

    try {
      connection = DatabaseConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY);
      preparedStatement.setLong(1, id);
      preparedStatement.execute();
    } catch (SQLException ex) {
      ex.printStackTrace(System.out);
    } finally {
      if (connection != null) {
        DatabaseConnection.close(connection);
      }
    }

  }

}
