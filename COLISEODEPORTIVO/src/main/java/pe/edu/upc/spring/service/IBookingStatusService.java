package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.BookingStatus;

public interface IBookingStatusService {
	public boolean insertar(BookingStatus bookingstatus);
	public boolean modificar(BookingStatus bookingstatus);
	public void eliminar(int bookingstatusId);
	public Optional<BookingStatus> listarId(int bookingstatusId);
	public List<BookingStatus> listar();
	
}
