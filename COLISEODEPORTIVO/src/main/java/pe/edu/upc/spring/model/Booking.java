package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="Booking")
public class Booking implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
		
	@ManyToOne
	@JoinColumn(name="clientId", nullable=false)
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="bookingstatusId", nullable=false)
	private BookingStatus bookingstatus;

	@Column(name="fieldscheduleId", nullable=false)
	private FieldSchedule fieldschedule;
	
	@Temporal(TemporalType.DATE)
	@Column(name="bookingDate", nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bookingDate;
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(int bookingId, Client client, BookingStatus bookingstatus, FieldSchedule fieldschedule,
			Date bookingDate) {
		super();
		this.bookingId = bookingId;
		this.client = client;
		this.bookingstatus = bookingstatus;
		this.fieldschedule = fieldschedule;
		this.bookingDate = bookingDate;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public BookingStatus getBookingstatus() {
		return bookingstatus;
	}

	public void setBookingstatus(BookingStatus bookingstatus) {
		this.bookingstatus = bookingstatus;
	}

	public FieldSchedule getFieldschedule() {
		return fieldschedule;
	}

	public void setFieldschedule(FieldSchedule fieldschedule) {
		this.fieldschedule = fieldschedule;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

}
