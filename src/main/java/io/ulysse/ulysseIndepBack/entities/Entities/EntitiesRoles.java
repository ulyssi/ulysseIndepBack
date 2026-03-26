package io.ulysse.ulysseIndepBack.entities.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "t_roles")
public class EntitiesRoles {

	private Integer roleID;
	private String roleName;
	private Integer companyID;
	private Integer userid;
	private Timestamp timestamp;

	public EntitiesRoles() {
		super();
	}

	@Id
	public Integer getRoleID() {
		return roleID;
	}
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String name) {
		this.roleName = roleName;
	}

	public Integer getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
