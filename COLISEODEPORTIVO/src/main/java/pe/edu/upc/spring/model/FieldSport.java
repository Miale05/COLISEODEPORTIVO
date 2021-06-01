package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="FieldSport")
public class FieldSport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fieldsportId;
		
	@ManyToOne
	@JoinColumn(name="sportId", nullable=false)
	private Sport sport;
	
	@ManyToOne
	@JoinColumn(name="sportfieldId", nullable=false)
	private SportField sportfield;

	public FieldSport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FieldSport(int fieldsportId, Sport sport, SportField sportfield) {
		super();
		this.fieldsportId = fieldsportId;
		this.sport = sport;
		this.sportfield = sportfield;
	}

	public int getFieldsportId() {
		return fieldsportId;
	}

	public void setFieldsportId(int fieldsportId) {
		this.fieldsportId = fieldsportId;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public SportField getSportfield() {
		return sportfield;
	}

	public void setSportfield(SportField sportfield) {
		this.sportfield = sportfield;
	}

}
