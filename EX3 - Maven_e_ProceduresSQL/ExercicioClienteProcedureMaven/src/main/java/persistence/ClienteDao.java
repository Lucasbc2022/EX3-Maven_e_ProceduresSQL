package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDao implements IClienteDao<Cliente> {
	
	private GenericDao gDao;

	public ClienteDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	private String callProcedureCliente(String opcao, Cliente cliente) throws ClassNotFoundException, SQLException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_cliente(?,?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, opcao);
		cs.setString(2, cliente.getCpf());
		cs.setString(3, cliente.getNome());
		cs.setString(4, cliente.getEmail());
		cs.setFloat(5, cliente.getLimite_cartao());
		cs.setString(6, cliente.getDt_nasci());
		cs.registerOutParameter(7, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(7);
		
		cs.close();
		c.close();
		
		return saida;
	
	}
	
	@Override
	public String inserir(Cliente cliente) throws SQLException, ClassNotFoundException {
		String saida = callProcedureCliente("I", cliente);
		return saida;
	}

	@Override
	public String atualizar(Cliente cliente) throws SQLException, ClassNotFoundException {
		String saida = callProcedureCliente("U", cliente);
		return saida;
	}

	@Override
	public String deletar(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "{CALL sp_cliente(?,?,?,?,?,?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, "D");
		cs.setString(2, cliente.getCpf());
		cs.setNull(3, Types.VARCHAR);
		cs.setNull(4, Types.VARCHAR);
		cs.setNull(5, Types.VARCHAR);
		cs.setNull(6, Types.VARCHAR);
		
		cs.registerOutParameter(7, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(7);
		
		cs.close();
		c.close();
		
		return saida;
	}
	
	@Override
	public Cliente consultar(Cliente cliente) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM Cliente WHERE CPF = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, cliente.getCpf());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			cliente.setNome(rs.getString("Nome"));
			cliente.setEmail(rs.getString("Email"));
			cliente.setLimite_cartao(rs.getFloat("Limite_de_credito"));
			cliente.setDt_nasci(rs.getString("Dt_nascimento"));
			System.out.println(cliente.toString());
		}
		rs.close();
		ps.close();
		c.close();	
		
		return cliente;
	}

	@Override
	public List<Cliente> listar() throws SQLException, ClassNotFoundException {
		List<Cliente> clientes = new ArrayList<>();
		
		Connection c = gDao.getConnection();
		String sql = "SELECT * FROM Cliente";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setCpf(rs.getString("CPF"));
			cliente.setNome(rs.getString("Nome"));
			cliente.setEmail(rs.getString("Email"));
			cliente.setLimite_cartao(rs.getFloat("Limite_de_credito"));
			cliente.setDt_nasci(rs.getString("Dt_Nascimento"));
			clientes.add(cliente);
		}
		rs.close();
		ps.close();
		c.close();		
		return clientes;
	}


}