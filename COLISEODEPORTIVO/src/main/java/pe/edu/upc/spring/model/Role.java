package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true)
	private int roleId;
	
	@Column(name="authority", length = 20, nullable=false)
	private String authority;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String authority) {
		super();
		this.authority = authority;
	}
	
	public Role(int roleId, String authority) {
		super();
		this.roleId = roleId;
		this.authority = authority;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	
}
