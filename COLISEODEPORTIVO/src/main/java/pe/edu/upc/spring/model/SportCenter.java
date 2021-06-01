package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SportCenter")
public class SportCenter implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sportcenterId;
	
	@Column(name="sportcenterName", length = 30, nullable=false)
	private String sportcenterName;
	
	@Column(name="sportcenterAddress", length = 100, nullable=false)
	private String sportcenterAddress;

	public SportCenter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SportCenter(int sportcenterId, String sportcenterName, String sportcenterAddress) {
		super();
		this.sportcenterId = sportcenterId;
		this.sportcenterName = sportcenterName;
		this.sportcenterAddress = sportcenterAddress;
	}

	public int getSportcenterId() {
		return sportcenterId;
	}

	public void setSportcenterId(int sportcenterId) {
		this.sportcenterId = sportcenterId;
	}

	public String getSportcenterName() {
		return sportcenterName;
	}

	public void setSportcenterName(String sportcenterName) {
		this.sportcenterName = sportcenterName;
	}

	public String getSportcenterAddress() {
		return sportcenterAddress;
	}

	public void setSportcenterAddress(String sportcenterAddress) {
		this.sportcenterAddress = sportcenterAddress;
	}

	
}
