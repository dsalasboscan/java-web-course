package com.educacionit.controller;

import com.educacionit.model.Action;
import com.educacionit.model.Cliente;
import com.educacionit.service.ClienteService;

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

  private ClienteService clienteService;

  public ClienteController() {
    clienteService = new ClienteService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    Action action = Action.valueOfLabel(req.getParameter("action"));

    if (action != null) {
      switch (action) {
        case CREATE:
          req.getRequestDispatcher("/WEB-INF/paginas/cliente/agregarCliente.jsp").forward(req, resp);
          break;
        case UPDATE:
          Cliente cliente = clienteService.getById(Long.parseLong(req.getParameter("idCliente")));
          req.setAttribute("cliente", cliente);
          req.getRequestDispatcher("/WEB-INF/paginas/cliente/editarCliente.jsp").forward(req, resp);
          break;
        case DELETE:
          Long id = Long.parseLong(req.getParameter("idCliente"));
          clienteService.delete(id);
          listarClientes(req, resp);
          break;
        default:
          listarClientes(req, resp);
          break;

      }
    } else {
      listarClientes(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Action action = Action.valueOfLabel(req.getParameter("action"));

    if (action == null) action = Action.LIST;

    switch (action) {
      case CREATE:
        clienteService.save(req);
        listarClientes(req, resp);
        break;
      case UPDATE:
        clienteService.update(req);
        listarClientes(req, resp);
        break;
      case FILTER:
        String filter = req.getParameter("filter");
        List<Cliente> clientes = clienteService.getClientes(filter);

        HttpSession session = req.getSession();
        session.setAttribute("clientes", clientes);
        req.getRequestDispatcher("/WEB-INF/paginas/clientes.jsp").forward(req, resp);
        break;
      default:
        listarClientes(req, resp);
        break;
    }
  }

  private void listarClientes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<Cliente> clientes = clienteService.getCLientes();

    HttpSession sesion = req.getSession();
    sesion.setAttribute("clientes", clientes);

    req.getRequestDispatcher("/WEB-INF/paginas/clientes.jsp").forward(req, resp);
  }
}
