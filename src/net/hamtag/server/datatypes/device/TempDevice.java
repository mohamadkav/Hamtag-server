package net.hamtag.server.datatypes.device;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEMP_DEVICES")
public class TempDevice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NUMBER", nullable = false)
	private String phoneNumber;
	
	@Column(name="VALID_UNTILL")
	private Date validUntill;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "TOKEN", nullable = false)
	private String token;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getValidUntill() {
		return validUntill;
	}

	public void setValidUntill(Date validUntill) {
		this.validUntill = validUntill;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
