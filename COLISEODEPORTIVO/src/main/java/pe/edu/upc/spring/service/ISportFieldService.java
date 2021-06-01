package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.SportField;

public interface ISportFieldService {
	public boolean insertar(SportField sportfield);
	public boolean modificar(SportField sportfield);
	public void eliminar(int sportfieldId);
	public Optional<SportField> listarId(int sportfieldId);
	public List<SportField> listar();
	
}
