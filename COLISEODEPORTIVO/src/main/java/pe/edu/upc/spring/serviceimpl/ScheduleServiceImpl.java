package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Schedule;
import pe.edu.upc.spring.repository.IScheduleRepository;
import pe.edu.upc.spring.service.IScheduleService;

@Service
public class ScheduleServiceImpl implements IScheduleService {

	@Autowired
	private IScheduleRepository dSchedule;
	
	@Override
	@Transactional
	public boolean insertar(Schedule schedule) {
		Schedule objSchedule = dSchedule.save(schedule);
		if(objSchedule == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Schedule schedule) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int scheduleId) {
		dSchedule.deleteById(scheduleId);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Schedule> listarId(int scheduleId) {
		return dSchedule.findById(scheduleId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Schedule> listar() {
		return dSchedule.findAll();
	}

}
