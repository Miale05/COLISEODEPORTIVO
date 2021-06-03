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

import pe.edu.upc.spring.model.SportField;
import pe.edu.upc.spring.model.SportCenter;
import pe.edu.upc.spring.model.Sport;

import pe.edu.upc.spring.service.ISportFieldService;
import pe.edu.upc.spring.service.ISportCenterService;
import pe.edu.upc.spring.service.ISportService;

@Controller
@RequestMapping("/sportfield")
public class SportFieldController {
	
	@Autowired
	private ISportFieldService sfService;
	
	@Autowired
	private ISportCenterService scService;
	
	@Autowired
	private ISportService sService;
	
	@RequestMapping("/home")
	public String irSportFieldBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoSportFields(Map<String, Object> model) {
		model.put("listaSportFields", sfService.listar());
		return "listSportField";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaSportCenters", scService.listar());
		model.addAttribute("listaSports", sService.listar());
		
		model.addAttribute("sportfield", new SportField());
		model.addAttribute("sportcenter", new SportCenter());
		model.addAttribute("sport", new Sport());
		
		return "sportfield";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute SportField objSportField, BindingResult binRes, Model model) throws ParseException { 
		
		if (binRes.hasErrors()) {
			model.addAttribute("listaSportCenters", scService.listar());
			model.addAttribute("listaSports", sService.listar());
			return "sportfield";
		}
		else {
			boolean flag = sfService.insertar(objSportField);
			if (flag)
				return "redirect:/sportfield/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/sportfield/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		
		Optional<SportField> objSportField = sfService.listarId(id);
		
		if(objSportField == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/sportfield/listar";
		}
		else {
			model.addAttribute("listaSportCenters", scService.listar());
			model.addAttribute("listaSports", sService.listar());
			
			if(objSportField.isPresent())
				objSportField.ifPresent(o -> model.addAttribute("sportfield", o));
			
			return "sportfield";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				sfService.eliminar(id);
				model.put("listaSportFields", sfService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaSportFields", sfService.listar());
		}
		return "listSportField";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSportFields", sfService.listar());
		return "listSportField";
	}
	
}
