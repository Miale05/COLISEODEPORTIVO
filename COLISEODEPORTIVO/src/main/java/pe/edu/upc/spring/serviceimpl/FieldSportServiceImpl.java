package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.FieldSport;
import pe.edu.upc.spring.service.IFieldSportService;
import pe.edu.upc.spring.repository.IFieldSportRepository;

@Service
public class FieldSportServiceImpl implements IFieldSportService {
	
	@Autowired
	private IFieldSportRepository dFieldSport;

	@Override
	@Transactional
	public boolean insertar(FieldSport fieldsport) {
		FieldSport objFieldSport = dFieldSport.save(fieldsport) ;
		if(objFieldSport != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public boolean modificar(FieldSport fieldsport) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int fieldsportId) {
		dFieldSport.deleteById(fieldsportId);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<FieldSport> listarId(int fieldsportId) {
		return dFieldSport.findById(fieldsportId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<FieldSport> listar() {
		return dFieldSport.findAll();
	}
	
	
}
