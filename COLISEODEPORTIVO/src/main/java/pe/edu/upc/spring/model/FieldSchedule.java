package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="FieldSchedule")
public class FieldSchedule implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fieldscheduleId;
		
	@ManyToOne
	@JoinColumn(name="scheduleId", nullable=false)
	private Schedule schedule;
	
	@ManyToOne
	@JoinColumn(name="sportfieldId", nullable=false)
	private SportField sportfield;

	@Column(name="fieldscheduleAvailable", nullable=false)
	private boolean fieldscheduleAvailable;
	
	@Column(name="fieldschedulePrice", nullable=false)
	private double fieldschedulePrice;
	
	public FieldSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FieldSchedule(int fieldscheduleId, Schedule schedule, SportField sportfield, boolean fieldscheduleAvailable,
			double fieldschedulePrice) {
		super();
		this.fieldscheduleId = fieldscheduleId;
		this.schedule = schedule;
		this.sportfield = sportfield;
		this.fieldscheduleAvailable = fieldscheduleAvailable;
		this.fieldschedulePrice = fieldschedulePrice;
	}

	public int getFieldscheduleId() {
		return fieldscheduleId;
	}

	public void setFieldscheduleId(int fieldscheduleId) {
		this.fieldscheduleId = fieldscheduleId;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public SportField getSportfield() {
		return sportfield;
	}

	public void setSportfield(SportField sportfield) {
		this.sportfield = sportfield;
	}

	public boolean isFieldscheduleAvailable() {
		return fieldscheduleAvailable;
	}

	public void setFieldscheduleAvailable(boolean fieldscheduleAvailable) {
		this.fieldscheduleAvailable = fieldscheduleAvailable;
	}

	public double getFieldschedulePrice() {
		return fieldschedulePrice;
	}

	public void setFieldschedulePrice(double fieldschedulePrice) {
		this.fieldschedulePrice = fieldschedulePrice;
	}

	
}
