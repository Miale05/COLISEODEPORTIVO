package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.SportCenter;

public interface ISportCenterService {
	public boolean insertar(SportCenter sportcenter);
	public boolean modificar(SportCenter sportcenter);
	public void eliminar(int sportcenterId);
	public Optional<SportCenter> listarId(int sportcenterId);
	public List<SportCenter> listar();
	
	
}
