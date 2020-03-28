package com.educacionit.dao;

import com.educacionit.model.Cliente;

import java.util.List;

public interface ClienteDao {

    void insert(Cliente cliente);

    void update(Cliente Cliente);

    Cliente getById(Long id);

    List<Cliente> getClientes();

    void delete(Long id);
}
