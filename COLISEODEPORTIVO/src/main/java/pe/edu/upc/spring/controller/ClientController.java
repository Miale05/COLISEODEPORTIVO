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

import pe.edu.upc.spring.model.User;
import pe.edu.upc.spring.model.Client;

import pe.edu.upc.spring.service.IUserService;
import pe.edu.upc.spring.service.IClientService;

@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private IClientService cService;
	
	@Autowired
	private IUserService uService;
	
	@RequestMapping("/home")
	public String irClientBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoClients(Map<String, Object> model) {
		model.put("listaClients", cService.listar());
		return "listClient";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaUsers", uService.listar());
		
		model.addAttribute("client", new Client());
		model.addAttribute("user", new User());
		
		return "client";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Client objClient, BindingResult binRes, Model model) throws ParseException { 
		
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsers", uService.listar());
			return "client";
		}
		else {
			boolean flag = cService.insertar(objClient);
			if (flag)
				return "redirect:/client/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/client/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		
		Optional<Client> objClient = cService.listarId(id);
		
		if(objClient == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/client/listar";
		}
		else {
			model.addAttribute("listaUsers", uService.listar());
			
			if(objClient.isPresent())
				objClient.ifPresent(o -> model.addAttribute("client", o));
			
			return "client";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				cService.eliminar(id);
				model.put("listaClients", cService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaClients", cService.listar());
		}
		return "listClient";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaClients", cService.listar());
		return "listClient";
	}
	
}
