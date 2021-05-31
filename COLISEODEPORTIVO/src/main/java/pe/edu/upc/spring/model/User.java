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
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="Users", uniqueConstraints = @UniqueConstraint(columnNames = "userEmail"))
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name="userNames", length = 30, nullable=false)
	private String userNames;
	
	@Column(name="userFirstName", length = 30, nullable=false)
	private String userFirstName;
	
	@Column(name="userLastName", length = 30, nullable=false)
	private String userLastName;
	
	@Column(name="userEmail", length = 50, nullable=false)
	private String userEmail;
	
	@Column(name="userPassword", length = 30, nullable=false)
	private String userPassword;
	
	@ManyToOne
	@JoinColumn(name="roleId", nullable=false)
	private Role role;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String userNames, String userFirstName, String userLastName,
			String userEmail, String userPassword, Role role) {
		super();
		this.userId = userId;
		this.userNames = userNames;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
