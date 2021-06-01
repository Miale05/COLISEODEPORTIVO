package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.SportField;
import pe.edu.upc.spring.service.ISportFieldService;
import pe.edu.upc.spring.repository.ISportFieldRepository;

@Service
public class SportFieldServiceImpl implements ISportFieldService {
	
	@Autowired
	private ISportFieldRepository dSportField;

	@Override
	@Transactional
	public boolean insertar(SportField sportfield) {
		SportField objSportField = dSportField.save(sportfield) ;
		if(objSportField != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public boolean modificar(SportField sportfield) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int sportfieldId) {
		dSportField.deleteById(sportfieldId);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<SportField> listarId(int sportfieldId) {
		return dSportField.findById(sportfieldId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SportField> listar() {
		return dSportField.findAll();
	}
	
	
}
