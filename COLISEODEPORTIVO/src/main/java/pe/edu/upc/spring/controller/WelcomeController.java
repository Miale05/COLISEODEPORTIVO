package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	
	@RequestMapping("/home")
	public String irPaginaBienvenida() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaLanding1() {
		return "index";
	}
	
	@RequestMapping("/nosotros")
	public String irPaginaLanding2() {
		return "nosotros";
	}
	
	@RequestMapping("/campos")
	public String irPaginaLanding3() {
		return "campos";
	}
}
