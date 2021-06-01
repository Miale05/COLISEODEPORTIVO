package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Booking;
import pe.edu.upc.spring.service.IBookingService;
import pe.edu.upc.spring.repository.IBookingRepository;

@Service
public class BookingServiceImpl implements IBookingService {
	
	@Autowired
	private IBookingRepository dBooking;

	@Override
	@Transactional
	public boolean insertar(Booking booking) {
		Booking objBooking = dBooking.save(booking) ;
		if(objBooking != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public boolean modificar(Booking booking) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int bookingId) {
		dBooking.deleteById(bookingId);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Booking> listarId(int bookingId) {
		return dBooking.findById(bookingId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Booking> listar() {
		return dBooking.findAll();
	}
	
	
}
