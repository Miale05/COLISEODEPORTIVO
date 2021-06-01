package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Client;

public interface IClientService {
	public boolean insertar(Client client);
	public boolean modificar(Client client);
	public void eliminar(int clientId);
	public Optional<Client> listarId(int clientId);
	public List<Client> listar();
	
}
