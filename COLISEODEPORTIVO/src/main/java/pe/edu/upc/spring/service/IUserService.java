package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.User;

public interface IUserService {
	public boolean insertar(User user);
	public boolean modificar(User user);
	public void eliminar(int userId);
	public Optional<User> listarId(int userId);
	public List<User> listar();
	
}
