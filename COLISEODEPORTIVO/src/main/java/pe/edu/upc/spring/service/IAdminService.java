package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Admin;

public interface IAdminService {
	public boolean insertar(Admin admin);
	public boolean modificar(Admin admin);
	public void eliminar(int adminId);
	public Optional<Admin> listarId(int adminId);
	public List<Admin> listar();
	
}
