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

import pe.edu.upc.spring.model.FieldSport;
import pe.edu.upc.spring.model.Sport;
import pe.edu.upc.spring.model.SportField;

import pe.edu.upc.spring.service.IFieldSportService;
import pe.edu.upc.spring.service.ISportService;
import pe.edu.upc.spring.service.ISportFieldService;

@Controller
@RequestMapping("/fieldsport")
public class FieldSportController {
	
	@Autowired
	private IFieldSportService fsService;
	
	@Autowired
	private ISportService sService;
	
	@Autowired
	private ISportFieldService sfService;
	
	@RequestMapping("/home")
	public String irFieldSportBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoFieldSports(Map<String, Object> model) {
		model.put("listaFieldSports", fsService.listar());
		return "listFieldSport";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaSportFields", sService.listar());
		model.addAttribute("listaSports", sfService.listar());
		
		model.addAttribute("fieldsport", new FieldSport());
		model.addAttribute("sport", new Sport());
		model.addAttribute("sportfield", new SportField());
		
		return "fieldsport";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute FieldSport objFieldSport, BindingResult binRes, Model model) throws ParseException { 
		
		if (binRes.hasErrors()) {
			model.addAttribute("listaSports", sService.listar());
			model.addAttribute("listaSportFields", sfService.listar());
			return "fieldsport";
		}
		else {
			boolean flag = fsService.insertar(objFieldSport);
			if (flag)
				return "redirect:/fieldsport/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/fieldsport/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		
		Optional<FieldSport> objFieldSport = fsService.listarId(id);
		
		if(objFieldSport == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/fieldsport/listar";
		}
		else {
			model.addAttribute("listaSports", sService.listar());
			model.addAttribute("listaSportFields", sfService.listar());
			
			if(objFieldSport.isPresent())
				objFieldSport.ifPresent(o -> model.addAttribute("fieldsport", o));
			
			return "fieldsport";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				fsService.eliminar(id);
				model.put("listaFieldSports", fsService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaFieldSports", fsService.listar());
		}
		return "listFieldSport";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaFieldSports", fsService.listar());
		return "listFieldSport";
	}
	
}
