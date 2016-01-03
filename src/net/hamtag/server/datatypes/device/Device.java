package net.hamtag.server.datatypes.device;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.hamtag.server.datatypes.ad.AdShown;
import net.hamtag.server.datatypes.purchase.Purchase;

@Entity
@Table(name = "DEVICES")
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "device")
	private Set<AdShown> adShown = new HashSet<AdShown>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "device")
	private Set<Purchase> purchases = new HashSet<Purchase>(0);

	@Column(name = "PHONE_NUMBER", unique = true, nullable = false)
	private String phoneNumber;

	@Column(name = "CHARGE")
	private Integer charge;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<AdShown> getAdShown() {
		return adShown;
	}

	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}

	public void setAdShown(Set<AdShown> adShown) {
		this.adShown = adShown;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(Integer charge) {
		this.charge = charge;
	}

}
