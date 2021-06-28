package pe.edu.upc.spring.controller;

import java.util.List;
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

import pe.edu.upc.spring.model.Sport;
import pe.edu.upc.spring.model.SportCenter;
import pe.edu.upc.spring.service.ISportService;

@Controller
@RequestMapping("/sport")
public class SportController {
	
	@Autowired
	private ISportService sService;
	
	@RequestMapping("/home")
	public String irSportBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoSports(Map<String, Object> model) {
		model.put("listaSports", sService.listar());
		return "listSport";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("sport", new Sport());
		return "sport";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Sport objSport, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "sport";
		else {
			List<Sport> deportes = sService.listar();
			for(int i = 0; i < deportes.size(); i++) {
				Sport c = deportes.get(i);
				if (objSport.getSportName().equals(c.getSportName())) {
					model.addAttribute("mensaje", "El deporte ya existe");
					return "redirect:/sport/irRegistrar";
				}
			}
			boolean flag = sService.insertar(objSport);
			if (flag)
				return "redirect:/sport/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/sport/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Sport> objSport = sService.listarId(id);
		if(objSport == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/sport/listar";
		}
		else {
			model.addAttribute("sport", objSport);
			return "sport";
		}
	}
	
	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				sService.eliminar(id);
				model.put("listaSports", sService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaSports", sService.listar());
		}
		return "listSport";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSports", sService.listar());
		return "listSport";
	}
	
}
