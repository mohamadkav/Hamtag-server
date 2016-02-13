package net.hamtag.server.api.response.types.device;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceSummaryDTO {
	@JsonProperty
	private Long allUsers;
	@JsonProperty
	private Long activeUsersByNews;
	@JsonProperty
	private Long activeUsersByAds;
	@JsonProperty
	private Long totalDebt;
	public Long getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(Long allUsers) {
		this.allUsers = allUsers;
	}
	public Long getActiveUsersByNews() {
		return activeUsersByNews;
	}
	public void setActiveUsersByNews(Long activeUsersByNews) {
		this.activeUsersByNews = activeUsersByNews;
	}
	public Long getActiveUsersByAds() {
		return activeUsersByAds;
	}
	public void setActiveUsersByAds(Long activeUsersByAds) {
		this.activeUsersByAds = activeUsersByAds;
	}
	public Long getTotalDebt() {
		return totalDebt;
	}
	public void setTotalDebt(Long totalDebt) {
		this.totalDebt = totalDebt;
	}
	
	
}
