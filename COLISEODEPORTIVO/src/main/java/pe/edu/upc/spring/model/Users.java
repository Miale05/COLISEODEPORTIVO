package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Users")
public class Users implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name="username", length = 30, unique=true)
	private String username;
	
	@Column(name="userNames", length = 30, nullable=false)
	private String userNames;
	
	@Column(name="userFirstName", length = 30, nullable=false)
	private String userFirstName;
	
	@Column(name="userLastName", length = 30, nullable=false)
	private String userLastName;
	
	@Column(name="userEmail", length = 50, nullable=false)
	private String userEmail;
	
	@Column(name="userPassword", unique=true)
	private String userPassword;
	
	//@ManyToOne
	//@JoinColumn(name="roleId", nullable=false)
	//private Role role;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private List<Role> roles;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int userId, String username, String userNames, String userFirstName, String userLastName,
			String userEmail, String userPassword, List<Role> roles) {
		super();
		this.userId = userId;
		this.username = username;
		this.userNames = userNames;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.roles = roles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
}
