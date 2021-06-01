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
@Table(name="SportField")
public class SportField implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sportfieldId;
	
	@Column(name="sportfieldName", length = 30, nullable=false)
	private String sportfieldName;
	
	@ManyToOne
	@JoinColumn(name="sportcenterId", nullable=false)
	private SportCenter sportcenter;

	public SportField() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SportField(int sportfieldId, String sportfieldName, SportCenter sportcenter) {
		super();
		this.sportfieldId = sportfieldId;
		this.sportfieldName = sportfieldName;
		this.sportcenter = sportcenter;
	}

	public int getSportfieldId() {
		return sportfieldId;
	}

	public void setSportfieldId(int sportfieldId) {
		this.sportfieldId = sportfieldId;
	}

	public String getSportfieldName() {
		return sportfieldName;
	}

	public void setSportfieldName(String sportfieldName) {
		this.sportfieldName = sportfieldName;
	}

	public SportCenter getSportcenter() {
		return sportcenter;
	}

	public void setSportcenter(SportCenter sportcenter) {
		this.sportcenter = sportcenter;
	}

}
