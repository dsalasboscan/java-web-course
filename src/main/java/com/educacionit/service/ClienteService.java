package com.educacionit.service;

import com.educacionit.dao.ClienteDao;
import com.educacionit.model.Cliente;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public class ClienteService {

  private ClienteDao clienteDao;

  public ClienteService() {
    clienteDao = new ClienteDao();
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

  public void update(HttpServletRequest request) {
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
    return clienteDao.getClientes();
  }

}
