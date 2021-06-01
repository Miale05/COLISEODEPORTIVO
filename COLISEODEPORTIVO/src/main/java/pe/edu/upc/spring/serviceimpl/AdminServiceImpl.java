package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Admin;
import pe.edu.upc.spring.service.IAdminService;
import pe.edu.upc.spring.repository.IAdminRepository;

@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	private IAdminRepository dAdmin;

	@Override
	@Transactional
	public boolean insertar(Admin admin) {
		Admin objAdmin = dAdmin.save(admin) ;
		if(objAdmin != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public boolean modificar(Admin admin) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int adminId) {
		dAdmin.deleteById(adminId);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Admin> listarId(int adminId) {
		return dAdmin.findById(adminId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Admin> listar() {
		return dAdmin.findAll();
	}
	
	
}
