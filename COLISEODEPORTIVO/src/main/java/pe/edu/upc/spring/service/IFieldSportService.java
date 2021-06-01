package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.FieldSport;

public interface IFieldSportService {
	public boolean insertar(FieldSport fieldsport);
	public boolean modificar(FieldSport fieldsport);
	public void eliminar(int fieldsportId);
	public Optional<FieldSport> listarId(int fieldsportId);
	public List<FieldSport> listar();
	
}
