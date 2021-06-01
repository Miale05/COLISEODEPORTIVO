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

import pe.edu.upc.spring.model.Admin;
import pe.edu.upc.spring.model.User;
import pe.edu.upc.spring.model.SportCenter;

import pe.edu.upc.spring.service.IAdminService;
import pe.edu.upc.spring.service.IUserService;
import pe.edu.upc.spring.service.ISportCenterService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IAdminService aService;
	
	@Autowired
	private IUserService uService;
	
	@Autowired
	private ISportCenterService scService;
	
	@RequestMapping("/home")
	public String irAdminBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoAdmins(Map<String, Object> model) {
		model.put("listaAdmins", aService.listar());
		return "listAdmin";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaSportCenters", scService.listar());
		model.addAttribute("listaUsers", uService.listar());
		
		model.addAttribute("admin", new Admin());
		model.addAttribute("user", new User());
		model.addAttribute("sportcenter", new SportCenter());
		
		return "admin";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Admin objAdmin, BindingResult binRes, Model model) throws ParseException { 
		
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsers", uService.listar());
			model.addAttribute("listaSportCenters", scService.listar());
			return "admin";
		}
		else {
			boolean flag = aService.insertar(objAdmin);
			if (flag)
				return "redirect:/admin/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/admin/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		
		Optional<Admin> objAdmin = aService.listarId(id);
		
		if(objAdmin == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/admin/listar";
		}
		else {
			model.addAttribute("listaUsers", uService.listar());
			model.addAttribute("listaSportCenters", scService.listar());
			
			if(objAdmin.isPresent())
				objAdmin.ifPresent(o -> model.addAttribute("admin", o));
			
			return "admin";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				aService.eliminar(id);
				model.put("listaAdmins", aService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaAdmins", aService.listar());
		}
		return "listAdmin";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAdmins", aService.listar());
		return "listAdmin";
	}
	
}
