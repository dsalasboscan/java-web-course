package com.educacionit.service;

import com.educacionit.dao.ClienteDao;
import com.educacionit.dao.ClienteFileDao;
import com.educacionit.dao.ClienteSqlDao;
import com.educacionit.model.Cliente;
import com.educacionit.model.DaoStrategy;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteService {

  private ClienteDao clienteDao;

  public ClienteService(DaoStrategy daoStrategy) {

    // Seleccionar base de datos a utilizar
    switch (daoStrategy) {
      case SQL:
        clienteDao = new ClienteSqlDao();
        break;
      case FILE:
        clienteDao = new ClienteFileDao();
        break;
    }
  }

  public Cliente getCliente(long id) {
    return clienteDao.getById(id);
  }

  public void save(HttpServletRequest req) {
    Cliente cliente = new Cliente();

    cliente.setFechaCreacion(new Date());
    cliente.setNombre(req.getParameter("nombre"));
    cliente.setApellido(req.getParameter("apellido"));
    cliente.setEmail(req.getParameter("email"));

    clienteDao.insert(cliente);
  }

  public void update(HttpServletRequest request) throws FileNotFoundException {
    Cliente cliente = clienteDao.getById(Long.parseLong(request.getParameter("idCliente")));
    String nombre = request.getParameter("nombre");
    String apellido = request.getParameter("apellido");
    String email = request.getParameter("email");

    if (nombre != null) cliente.setNombre(nombre);
    if (apellido != null) cliente.setApellido(apellido);
    if (email != null) cliente.setEmail(email);

    clienteDao.update(cliente);
  }

  public void delete(long id) {
    clienteDao.delete(id);
  }

  public List<Cliente> getAll() {
    return this.getAll(null);
  }

  public List<Cliente> getAll(String filter) {
    List<Cliente> clientes;
    if (filter == null || filter.isEmpty()) {
      clientes = clienteDao.getClientes();
    } else {
      clientes = clienteDao.getClientes().stream()
              .filter(c -> c.getApellido().equalsIgnoreCase(filter))
              .collect(Collectors.toList());
    }

    return clientes;
  }

}
