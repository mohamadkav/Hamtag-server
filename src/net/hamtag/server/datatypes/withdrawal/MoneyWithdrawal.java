package net.hamtag.server.datatypes.withdrawal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.hamtag.server.datatypes.device.Device;

@Entity
@Table(name = "MONEY_WITHDRAWAL")
public class MoneyWithdrawal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "USER_COMMENTS")
	private String userComments;
	
	
	@Column(name = "HAMTAG_COMMENTS")
	private String hamtagComments;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICEID", nullable = false)
	private Device device;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getHamtagComments() {
		return hamtagComments;
	}

	public void setHamtagComments(String hamtagComments) {
		this.hamtagComments = hamtagComments;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
	
}
