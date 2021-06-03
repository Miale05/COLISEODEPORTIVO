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

import pe.edu.upc.spring.model.Booking;
import pe.edu.upc.spring.model.User;
import pe.edu.upc.spring.model.BookingStatus;
import pe.edu.upc.spring.model.FieldSchedule;

import pe.edu.upc.spring.service.IBookingService;
import pe.edu.upc.spring.service.IUserService;
import pe.edu.upc.spring.service.IBookingStatusService;
import pe.edu.upc.spring.service.IFieldScheduleService;

@Controller
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private IBookingService bService;
	
	@Autowired
	private IUserService uService;
	
	@Autowired
	private IBookingStatusService bsService;
	
	@Autowired
	private IFieldScheduleService fsService;
	
	@RequestMapping("/home")
	public String irBookingBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoBookings(Map<String, Object> model) {
		model.put("listaBookings", bService.listar());
		return "listBooking";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("listaUsers", uService.listar());
		model.addAttribute("listaBookingStatus", bsService.listar());
		model.addAttribute("listaFieldSchedules", fsService.listar());
		
		model.addAttribute("booking", new Booking());
		model.addAttribute("user", new User());
		model.addAttribute("bookingstatus", new BookingStatus());
		model.addAttribute("fieldschedule", new FieldSchedule());
		
		return "booking";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Booking objBooking, BindingResult binRes, Model model) throws ParseException { 
		
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsers", uService.listar());
			model.addAttribute("listaBookingStatus", bsService.listar());
			model.addAttribute("listaFieldSchedules", fsService.listar());
			return "booking";
		}
		else {
			boolean flag = bService.insertar(objBooking);
			if (flag)
				return "redirect:/booking/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/booking/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		
		Optional<Booking> objBooking = bService.listarId(id);
		
		if(objBooking == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/booking/listar";
		}
		else {
			model.addAttribute("listaUsers", uService.listar());
			model.addAttribute("listaBookingStatus", bsService.listar());
			model.addAttribute("listaFieldSchedules", fsService.listar());
			
			if(objBooking.isPresent())
				objBooking.ifPresent(o -> model.addAttribute("booking", o));
			
			return "booking";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				bService.eliminar(id);
				model.put("listaBookings", bService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaBookings", bService.listar());
		}
		return "listBooking";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaBookings", bService.listar());
		return "listBooking";
	}
	
}
