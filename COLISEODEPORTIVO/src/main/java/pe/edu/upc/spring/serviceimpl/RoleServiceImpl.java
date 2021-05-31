package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Role;
import pe.edu.upc.spring.repository.IRoleRepository;
import pe.edu.upc.spring.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleRepository dRole;
	
	@Override
	@Transactional
	public boolean insertar(Role role) {
		Role objRole = dRole.save(role);
		if(objRole == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Role role) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int roleId) {
		dRole.deleteById(roleId);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Role> listarId(int roleId) {
		return dRole.findById(roleId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> listar() {
		return dRole.findAll();
	}

}
