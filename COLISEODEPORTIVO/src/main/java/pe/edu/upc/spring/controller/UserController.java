package pe.edu.upc.spring.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Users;
import pe.edu.upc.spring.model.Role;

import pe.edu.upc.spring.service.IUserService;
import pe.edu.upc.spring.service.IRoleService;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService uService;
	
	@Autowired
	private IRoleService rService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/home")
	public String irUserBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoUsuarios(Map<String, Object> model) {
		model.put("listaUsers", uService.listar());
		return "listUser";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("user", new Users());
		
		return "user";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Users objUser, BindingResult binRes, Model model) throws ParseException { 
		
		if (binRes.hasErrors()) {
			model.addAttribute("listaRoles", rService.listar());
			return "user";
		}
		else {
			Role userRole = new Role("ROLE_USER");
			List<Role> roles = new ArrayList<Role>();
			roles.add(userRole);
			objUser.setRoles(roles);
			String bcryptPassword = passwordEncoder.encode(objUser.getUserPassword());
			objUser.setUserPassword(bcryptPassword);
			boolean flag = uService.insertar(objUser);
			if (flag)
				return "redirect:/login";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/user/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		
		Optional<Users> objUser = uService.listarId(id);
		
		if(objUser == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/user/listar";
		}
		else {
			model.addAttribute("listaRoles", rService.listar());
			
			if(objUser.isPresent())
				objUser.ifPresent(o -> model.addAttribute("user", o));
			
			return "user";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				uService.eliminar(id);
				model.put("listaUsers", uService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaUsers", uService.listar());
		}
		return "listUser";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaUsers", uService.listar());
		return "listUser";
	}
	
}
