package persistence;

import java.sql.SQLException;
import java.util.List;

public interface IClienteDao<Cliente> {

	public String inserir(Cliente cliente)    throws SQLException,  ClassNotFoundException;
	public String atualizar(Cliente ccliente)  throws SQLException,  ClassNotFoundException;
	public String deletar(Cliente cliente)    throws SQLException,  ClassNotFoundException;
	public Cliente consultar(Cliente ccliente) throws SQLException,  ClassNotFoundException;
	public List<Cliente> listar()       throws SQLException,  ClassNotFoundException;
}
