package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.BookingStatus;
import pe.edu.upc.spring.repository.IBookingStatusRepository;
import pe.edu.upc.spring.service.IBookingStatusService;

@Service
public class BookingStatusServiceImpl implements IBookingStatusService {

	@Autowired
	private IBookingStatusRepository dBookingStatus;
	
	@Override
	@Transactional
	public boolean insertar(BookingStatus bookingstatus) {
		BookingStatus objBookingStatus = dBookingStatus.save(bookingstatus);
		if(objBookingStatus == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(BookingStatus bookingstatus) {
		return true;
	}

	@Override
	@Transactional
	public void eliminar(int bookingstatusId) {
		dBookingStatus.deleteById(bookingstatusId);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<BookingStatus> listarId(int bookingstatusId) {
		return dBookingStatus.findById(bookingstatusId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingStatus> listar() {
		return dBookingStatus.findAll();
	}

}
