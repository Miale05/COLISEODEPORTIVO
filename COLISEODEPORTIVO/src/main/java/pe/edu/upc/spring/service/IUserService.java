package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Users;

public interface IUserService {
	public boolean insertar(Users user);
	public boolean modificar(Users user);
	public void eliminar(int userId);
	public Optional<Users> listarId(int userId);
	public List<Users> listar();
	
}
