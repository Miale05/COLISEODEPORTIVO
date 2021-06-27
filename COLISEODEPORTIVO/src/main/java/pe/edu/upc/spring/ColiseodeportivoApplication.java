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
		Role userRole = new Role("ROLE_ADMIN");
		List<Role> roles = new ArrayList<Role>();
		roles.add(userRole);
		
		Users user = new Users(1,"admin", "admin", "admin", "admin", "admin", passwordEncoder.encode("admin"), roles);
		uService.insertar(user);
	}

}
