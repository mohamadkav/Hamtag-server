package net.hamtag.server.api.response.types.ads;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdShownDTO {
	@JsonProperty
	private Long id;
	@JsonProperty("shown-time")
	private Integer shownTime;
	@JsonProperty("show-date")
	private Date showDate;
	@JsonProperty("prcentage")
	private Integer percentage;
	@JsonProperty("device")
	private String deviceUserName;
	@JsonProperty("ad-id")
	private Long adId;
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
	public Integer getPercentage() {
		return percentage;
	}
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
	public String getDeviceUserName() {
		return deviceUserName;
	}
	public void setDeviceUserName(String deviceUserName) {
		this.deviceUserName = deviceUserName;
	}
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	
}
