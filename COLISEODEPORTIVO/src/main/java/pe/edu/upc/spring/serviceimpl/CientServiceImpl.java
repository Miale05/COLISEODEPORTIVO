package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Client;
import pe.edu.upc.spring.service.IClientService;
import pe.edu.upc.spring.repository.IClientRepository;

@Service
public class CientServiceImpl implements IClientService {
	
	@Autowired
	private IClientRepository dClient;

	@Override
	@Transactional
	public boolean insertar(Client client) {
		Client objClient = dClient.save(client) ;
		if(objClient != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public boolean modificar(Client client) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int clientId) {
		dClient.deleteById(clientId);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Client> listarId(int clientId) {
		return dClient.findById(clientId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Client> listar() {
		return dClient.findAll();
	}
	
	
}
