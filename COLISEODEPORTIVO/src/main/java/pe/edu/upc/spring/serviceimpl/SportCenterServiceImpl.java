package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.SportCenter;
import pe.edu.upc.spring.repository.ISportCenterRepository;
import pe.edu.upc.spring.service.ISportCenterService;

@Service
public class SportCenterServiceImpl implements ISportCenterService {

	@Autowired
	private ISportCenterRepository dSportCenter;
	
	@Override
	@Transactional
	public boolean insertar(SportCenter sportcenter) {
		SportCenter objSportCenter = dSportCenter.save(sportcenter);
		if(objSportCenter == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(SportCenter sportcenter) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int sportcenterId) {
		dSportCenter.deleteById(sportcenterId);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SportCenter> listarId(int sportcenterId) {
		return dSportCenter.findById(sportcenterId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SportCenter> listar() {
		return dSportCenter.findAll();
	}

}
