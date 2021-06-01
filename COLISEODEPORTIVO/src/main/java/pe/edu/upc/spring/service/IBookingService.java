package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Booking;

public interface IBookingService {
	public boolean insertar(Booking booking);
	public boolean modificar(Booking booking);
	public void eliminar(int bookingId);
	public Optional<Booking> listarId(int bookingId);
	public List<Booking> listar();
	
}
