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

import pe.edu.upc.spring.model.SportCenter;
import pe.edu.upc.spring.service.ISportCenterService;

@Controller
@RequestMapping("/sportcenter")
public class SportCenterController {
	
	@Autowired
	private ISportCenterService scService;
	
	@RequestMapping("/home")
	public String irSportCenterBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoSportCenters(Map<String, Object> model) {
		model.put("listaSportCenters", scService.listar());
		return "listSportCenter";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("sportcenter", new SportCenter());
		return "sportcenter";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute SportCenter objSportCenter, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "sportcenter";
		else {
			boolean flag = scService.insertar(objSportCenter);
			if (flag)
				return "redirect:/sportcenter/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/sportcenter/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<SportCenter> objSportCenter = scService.listarId(id);
		if(objSportCenter == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/sportcenter/listar";
		}
		else {
			model.addAttribute("sportcenter", objSportCenter);
			return "sportcenter";
		}
	}
	
	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				scService.eliminar(id);
				model.put("listaSportCenters", scService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaSportCenters", scService.listar());
		}
		return "listSportCenter";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSportCenters", scService.listar());
		return "listSportCenter";
	}
	
}
