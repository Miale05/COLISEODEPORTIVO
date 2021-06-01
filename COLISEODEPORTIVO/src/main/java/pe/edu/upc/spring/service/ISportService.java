package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Sport;

public interface ISportService {
	public boolean insertar(Sport sport);
	public boolean modificar(Sport sport);
	public void eliminar(int sportId);
	public Optional<Sport> listarId(int sportId);
	public List<Sport> listar();
	
	
}
