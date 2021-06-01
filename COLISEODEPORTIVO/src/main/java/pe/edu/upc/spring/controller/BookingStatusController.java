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

import pe.edu.upc.spring.model.BookingStatus;
import pe.edu.upc.spring.service.IBookingStatusService;

@Controller
@RequestMapping("/bookingstatus")
public class BookingStatusController {
	
	@Autowired
	private IBookingStatusService bsService;
	
	@RequestMapping("/home")
	public String irBookingStatusBienvenido() {
		return "home";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoBookingStatus(Map<String, Object> model) {
		model.put("listaBookingStatus", bsService.listar());
		return "listBookingStatus";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("bookingstatus", new BookingStatus());
		return "bookingstatus";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute BookingStatus objBookingStatus, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "bookingstatus";
		else {
			boolean flag = bsService.insertar(objBookingStatus);
			if (flag)
				return "redirect:/bookingstatus/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/bookingstatus/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<BookingStatus> objBookingStatus = bsService.listarId(id);
		if(objBookingStatus == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/bookingstatus/listar";
		}
		else {
			model.addAttribute("bookingstatus", objBookingStatus);
			return "bookingstatus";
		}
	}
	
		
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id != null && id>0) {
				bsService.eliminar(id);
				model.put("listaBookingStatus", bsService.listar());
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaBookingStatus", bsService.listar());
		}
		return "listBookingStatus";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaBookingStatus", bsService.listar());
		return "listBookingStatus";
	}
	
}
