package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Role;

public interface IRoleService {
	public boolean insertar(Role role);
	public boolean modificar(Role role);
	public void eliminar(int roleId);
	public Optional<Role> listarId(int roleId);
	public List<Role> listar();
	
	
}
