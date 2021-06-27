package pe.edu.upc.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.spring.model.Role;
import pe.edu.upc.spring.model.Users;
import pe.edu.upc.spring.service.IUserService;

@SpringBootApplication
public class ColiseodeportivoApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUserService uService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ColiseodeportivoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws  Exception {
		Role adminRole = new Role("ROLE_ADMIN");
		List<Role> adminRoles = new ArrayList<Role>();
		adminRoles.add(adminRole);
		
		Users admin = new Users(1,"admin", "admin", "admin", "admin", "admin", passwordEncoder.encode("admin"), adminRoles);
		uService.insertar(admin);
		
		Role userRole = new Role("ROLE_USER");
		List<Role> userRoles = new ArrayList<Role>();
		userRoles.add(userRole);
		
		Users user1 = new Users(2, "rafo4950", "Rafael", "Bartra","Zevallos",
				"rafo.b.1998@gmail.com", passwordEncoder.encode("123456"), userRoles);
		uService.insertar(user1);
		
		Users user2 = new Users(3, "miguel", "Miguel", "Lalala","Lelele",
				"miguel@gmail.com", passwordEncoder.encode("123456"), userRoles);
		uService.insertar(user2);
	}

}
