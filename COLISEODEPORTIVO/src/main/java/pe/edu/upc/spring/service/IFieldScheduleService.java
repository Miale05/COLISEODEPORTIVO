package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.FieldSchedule;

public interface IFieldScheduleService {
	public boolean insertar(FieldSchedule fieldschedule);
	public boolean modificar(FieldSchedule fieldschedule);
	public void eliminar(int fieldscheduleId);
	public Optional<FieldSchedule> listarId(int fieldscheduleId);
	public List<FieldSchedule> listar();
	
}
