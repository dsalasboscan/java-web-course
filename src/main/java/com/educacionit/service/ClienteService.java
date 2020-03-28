package com.educacionit.service;

import com.educacionit.dao.ClienteDao;
import com.educacionit.dao.ClienteFileDaoImpl;
import com.educacionit.model.Cliente;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteService {

    private ClienteDao clienteDao;

    public ClienteService() {
        //clienteDao = new ClienteSqlDaoImpl();
        clienteDao = new ClienteFileDaoImpl();
    }

    public List<Cliente> getCLientes() {
        return this.getClientes(null);
    }

    public List<Cliente> getClientes(String filter) {
        List<Cliente> clientes;

        if(filter == null || filter.isEmpty()) {
            clientes = clienteDao.getClientes();
        } else {
            clientes = clienteDao.getClientes().stream()
                    .filter(c -> c.getApellido().equalsIgnoreCase(filter))
                    .collect(Collectors.toList());
        }

        return clientes;
    }

    public void save(HttpServletRequest request) {
        Cliente cliente = new Cliente();

        cliente.setFechaCreacion(new Date());

        cliente.setNombre(request.getParameter("nombre"));
        cliente.setApellido(request.getParameter("apellido"));
        cliente.setEmail(request.getParameter("email"));

        clienteDao.insert(cliente);
    }

    public Cliente getById(Long id) {
        return clienteDao.getById(id);
    }

    public void update(HttpServletRequest request) {
        Cliente cliente = clienteDao.getById(Long.parseLong(request.getParameter("idCliente")));

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");

        if (nombre != null) cliente.setNombre(nombre);
        if (apellido != null) cliente.setApellido(apellido);
        if (email != null) cliente.setEmail(email);

        cliente.setFechaCreacion(new Date());

        clienteDao.update(cliente);
    }

    public void delete(Long id) {
        clienteDao.delete(id);
    }
}