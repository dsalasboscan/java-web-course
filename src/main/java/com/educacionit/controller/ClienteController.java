package com.educacionit.controller;

import com.educacionit.dao.ClienteDao;
import com.educacionit.model.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientes")
public class ClienteController extends HttpServlet {

  private ClienteDao clienteDao;

  public ClienteController() {
    clienteDao = new ClienteDao();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    List<Cliente> clientes = clienteDao.getClientes();
    HttpSession sesion = req.getSession();
    sesion.setAttribute("clientes", clientes);

    req.getRequestDispatcher("/WEB-INF/paginas/clientes.jsp").forward(req, resp);
  }
}
