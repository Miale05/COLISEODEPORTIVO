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

import pe.edu.upc.spring.model.Schedule;
import pe.edu.upc.spring.service.IScheduleService;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	private IScheduleService schService;
	
	@RequestMapping("/home")
	public String irScheduleBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoSchedules(Map<String, Object> model) {
		model.put("listaSchedules", schService.listar());
		return "listSchedule";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("schedule", new Schedule());
		return "schedule";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Schedule objSchedule, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "schedule";
		else {
			List<Schedule> horarios = schService.listar();
			for(int i = 0; i < horarios.size(); i++) {
				Schedule c = horarios.get(i);
				if (objSchedule.getScheduleStart().equals(c.getScheduleStart()) && objSchedule.getScheduleEnd().equals(c.getScheduleEnd())) {
					model.addAttribute("mensaje", "El horario ya existe");
					model.addAttribute("schedule", new Schedule());
					return "schedule";				}
			}
			
			boolean flag = schService.insertar(objSchedule);
			if (flag)
				return "redirect:/schedule/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/schedule/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Schedule> objSchedule = schService.listarId(id);
		if(objSchedule == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/schedule/listar";
		}
		else {
			model.addAttribute("schedule", objSchedule);
			return "schedule";
		}
	}
	
	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				schService.eliminar(id);
				model.put("listaSchedules", schService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaSchedules", schService.listar());
		}
		return "listSchedule";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaSchedules", schService.listar());
		return "listSchedule";
	}
	
}
