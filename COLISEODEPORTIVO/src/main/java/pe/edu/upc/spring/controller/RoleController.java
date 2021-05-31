package pe.edu.upc.spring.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Role;
import pe.edu.upc.spring.service.IRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private IRoleService rService;
	
	@RequestMapping("/home")
	public String irRoleBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irRole(Map<String, Object> model) {
		model.put("listaRoles", rService.listar());
		return "listRole";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("role", new Role());
		return "role";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Role objRole, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "role";
		else {
			boolean flag = rService.insertar(objRole);
			if (flag)
				return "redirect:/role/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/role/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Role> objRole = rService.listarId(id);
		if(objRole == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/role/listar";
		}
		else {
			model.addAttribute("role", objRole);
			return "role";
		}
	}
	
	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				rService.eliminar(id);
				model.put("listaRoles", rService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaRoles", rService.listar());
		}
		return "listRole";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaRoles", rService.listar());
		return "listRole";
	}
	
}
