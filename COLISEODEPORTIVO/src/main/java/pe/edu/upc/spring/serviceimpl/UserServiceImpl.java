package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Users;
import pe.edu.upc.spring.service.IUserService;
import pe.edu.upc.spring.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository dUser;

	
	@Override
	@Transactional
	public boolean insertar(Users user) {
		
		Users objUser = dUser.save(user);
		if(objUser != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public boolean modificar(Users user) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int userId) {
		dUser.deleteById(userId);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Users> listarId(int userId) {
		return dUser.findById(userId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Users> listar() {
		return dUser.findAll();
	}
	
	
}
