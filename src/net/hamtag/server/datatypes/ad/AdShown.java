package net.hamtag.server.datatypes.ad;


import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.hamtag.server.datatypes.device.Device;
@Entity
@Table(name="AD_DISPLAY", indexes = {@Index(columnList = "ADID", name = "AD_INDEX"),
		@Index(columnList = "SHOWDATE", name = "AD_SHOW_DATE_INDEX"),
		@Index(columnList = "DEVICEID", name = "AD_SHOWN_DEVICE_INDEX")})
public class AdShown {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
	private Long id;
	
	@Column(name = "SHOWN_TIME")
	private Integer shownTime;
	
	@Column(name="SHOWDATE")
	private Date showDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ADID", nullable = false)
	private Ad ad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICEID", nullable = false)
	private Device device;
	

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getShownTime() {
		return shownTime;
	}

	public void setShownTime(Integer shownTime) {
		this.shownTime = shownTime;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}
	
	
}
