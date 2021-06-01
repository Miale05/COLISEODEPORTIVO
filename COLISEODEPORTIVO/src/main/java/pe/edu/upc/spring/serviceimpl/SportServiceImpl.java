package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Sport;
import pe.edu.upc.spring.repository.ISportRepository;
import pe.edu.upc.spring.service.ISportService;

@Service
public class SportServiceImpl implements ISportService {

	@Autowired
	private ISportRepository dSport;
	
	@Override
	@Transactional
	public boolean insertar(Sport sport) {
		Sport objSport = dSport.save(sport);
		if(objSport == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Sport sport) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int sportId) {
		dSport.deleteById(sportId);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Sport> listarId(int sportId) {
		return dSport.findById(sportId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sport> listar() {
		return dSport.findAll();
	}

}
