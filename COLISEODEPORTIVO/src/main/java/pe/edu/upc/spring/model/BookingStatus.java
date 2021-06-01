package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BookingStatus")
public class BookingStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingstatusId;
	
	@Column(name="bookingstatusName", length = 30, nullable=false)
	private String bookingstatusName;

	public BookingStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingStatus(int bookingstatusId, String bookingstatusName) {
		super();
		this.bookingstatusId = bookingstatusId;
		this.bookingstatusName = bookingstatusName;
	}

	public int getBookingstatusId() {
		return bookingstatusId;
	}

	public void setBookingstatusId(int bookingstatusId) {
		this.bookingstatusId = bookingstatusId;
	}

	public String getBookingstatusName() {
		return bookingstatusName;
	}

	public void setBookingstatusName(String bookingstatusName) {
		this.bookingstatusName = bookingstatusName;
	}

	
}
