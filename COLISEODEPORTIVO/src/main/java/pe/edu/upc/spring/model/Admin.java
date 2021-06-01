package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Admin")
public class Admin implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
		
	@OneToOne
	@JoinColumn(name="userId", nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="sportcenterId", nullable=false)
	private SportCenter sportcenter;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int adminId, User user, SportCenter sportcenter) {
		super();
		this.adminId = adminId;
		this.user = user;
		this.sportcenter = sportcenter;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SportCenter getSportcenter() {
		return sportcenter;
	}

	public void setSportcenter(SportCenter sportcenter) {
		this.sportcenter = sportcenter;
	}

}
