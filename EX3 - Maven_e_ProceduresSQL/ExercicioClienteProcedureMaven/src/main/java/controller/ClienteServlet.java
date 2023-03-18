package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.CDATASection;

import model.Cliente;
import persistence.ClienteDao;
import persistence.GenericDao;



@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ClienteServlet() {
        super();
   
    }



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String botao = request.getParameter("botao");

		GenericDao gDao = new GenericDao();
		ClienteDao cDao = new ClienteDao(gDao);

		Cliente cliente = new Cliente();
		List<Cliente> clientes = new ArrayList<>();
		String erro = "";
		String saida = "";

		try {
			if (botao.equalsIgnoreCase("listar")) {
				clientes = cDao.listar();
			} else {
				cliente.setCpf((request.getParameter("cpf")));
				if (botao.equalsIgnoreCase("buscar") || botao.equalsIgnoreCase("excluir")) {
					if (botao.equalsIgnoreCase("buscar")) {
						cliente = cDao.consultar(cliente);
					} else {
						saida = cDao.deletar(cliente);
						cliente = new Cliente();
					}
				} else {
					cliente.setNome(request.getParameter("nome"));
					cliente.setEmail(request.getParameter("email"));
					cliente.setLimite_cartao(Float.parseFloat(request.getParameter("limite_credito")));
					cliente.setDt_nasci(request.getParameter("dt_nasci"));
					if (botao.equalsIgnoreCase("inserir")) {
						saida = cDao.inserir(cliente);
						cliente = new Cliente();
					} else {
						saida = cDao.atualizar(cliente);
						cliente = new Cliente();
					}
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("cliente", cliente);
			request.setAttribute("clientes", clientes);

			RequestDispatcher dispatcher = request.getRequestDispatcher("cliente.jsp");
			dispatcher.forward(request, response);
		}
	}

}
