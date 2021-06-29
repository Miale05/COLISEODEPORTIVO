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

import pe.edu.upc.spring.model.FieldSchedule;
import pe.edu.upc.spring.model.Schedule;
import pe.edu.upc.spring.model.SportField;

import pe.edu.upc.spring.service.IFieldScheduleService;
import pe.edu.upc.spring.service.IScheduleService;
import pe.edu.upc.spring.service.ISportFieldService;

@Controller
@RequestMapping("/fieldschedule")
public class FieldScheduleController {
	
	@Autowired
	private IFieldScheduleService fschService;
	
	@Autowired
	private IScheduleService schService;
	
	@Autowired
	private ISportFieldService sfService;
	
	@RequestMapping("/home")
	public String irFieldScheduleBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoFieldSchedules(Map<String, Object> model) {
		model.put("listaFieldSchedules", fschService.listar());
		return "listFieldSchedule";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaSportFields", sfService.listar());
		model.addAttribute("listaSchedules", schService.listar());
		
		model.addAttribute("fieldschedule", new FieldSchedule());
		model.addAttribute("schedule", new Schedule());
		model.addAttribute("sportfield", new SportField());
		
		return "fieldschedule";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute FieldSchedule objFieldSchedule, BindingResult binRes, Model model) throws ParseException { 
		
		if (binRes.hasErrors()) {
			model.addAttribute("listaSchedules", schService.listar());
			model.addAttribute("listaSportFields", sfService.listar());
			return "fieldschedule";
		}
		else {
			if (objFieldSchedule.getFieldschedulePrice() < 0) {
				model.addAttribute("mensaje", "El Precio debe ser un numero positivo");
				model.addAttribute("listaSportFields", sfService.listar());
				model.addAttribute("listaSchedules", schService.listar());
				
				model.addAttribute("fieldschedule", new FieldSchedule());
				model.addAttribute("schedule", new Schedule());
				model.addAttribute("sportfield", new SportField());
				
				return "fieldschedule";
			}
			String start = objFieldSchedule.getSchedule().getScheduleStart();
			String end = objFieldSchedule.getSchedule().getScheduleEnd();
			
			List<FieldSchedule> horarios = fschService.listar();
			for(int i = 0; i < horarios.size(); i++) {
				FieldSchedule c = horarios.get(i);
				if (start.equals(c.getSchedule().getScheduleStart()) && end.equals(c.getSchedule().getScheduleEnd())) {
					model.addAttribute("mensaje", "El horario ya existe");
					model.addAttribute("listaSportFields", sfService.listar());
					model.addAttribute("listaSchedules", schService.listar());
					
					model.addAttribute("fieldschedule", new FieldSchedule());
					model.addAttribute("schedule", new Schedule());
					model.addAttribute("sportfield", new SportField());
					
					return "fieldschedule";
				}
			}
			boolean flag = fschService.insertar(objFieldSchedule);
			if (flag)
				return "redirect:/fieldschedule/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/fieldschedule/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		
		Optional<FieldSchedule> objFieldSchedule = fschService.listarId(id);
		
		if(objFieldSchedule == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/fieldschedule/listar";
		}
		else {
			model.addAttribute("listaSchedules", schService.listar());
			model.addAttribute("listaSportFields", sfService.listar());
			
			if(objFieldSchedule.isPresent())
				objFieldSchedule.ifPresent(o -> model.addAttribute("fieldschedule", o));
			
			return "fieldschedule";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				fschService.eliminar(id);
				model.put("listaFieldSchedules", fschService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaFieldSchedules", fschService.listar());
		}
		return "listFieldSchedule";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaFieldSchedules", fschService.listar());
		return "listFieldSchedule";
	}
	
}
