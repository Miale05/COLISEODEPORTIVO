package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.FieldSchedule;
import pe.edu.upc.spring.service.IFieldScheduleService;
import pe.edu.upc.spring.repository.IFieldScheduleRepository;

@Service
public class FieldScheduleServiceImpl implements IFieldScheduleService {
	
	@Autowired
	private IFieldScheduleRepository dFieldSchedule;

	@Override
	@Transactional
	public boolean insertar(FieldSchedule fieldschedule) {
		FieldSchedule objFieldSchedule = dFieldSchedule.save(fieldschedule) ;
		if(objFieldSchedule != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public boolean modificar(FieldSchedule fieldschedule) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int fieldscheduleId) {
		dFieldSchedule.deleteById(fieldscheduleId);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<FieldSchedule> listarId(int fieldscheduleId) {
		return dFieldSchedule.findById(fieldscheduleId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<FieldSchedule> listar() {
		return dFieldSchedule.findAll();
	}
	
	
}
