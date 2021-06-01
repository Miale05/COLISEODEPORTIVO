package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Schedule;

public interface IScheduleService {
	public boolean insertar(Schedule schedule);
	public boolean modificar(Schedule schedule);
	public void eliminar(int scheduleId);
	public Optional<Schedule> listarId(int scheduleId);
	public List<Schedule> listar();
	
	
}
