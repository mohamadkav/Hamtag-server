package net.hamtag.server.api.response.types.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoDTO {
	@JsonProperty("username")
	private String userName;
	@JsonProperty("num-ads-by-user")
	private Long adsByUser;
	@JsonProperty("num-news-by-user")
	private Long newsByUser;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getAdsByUser() {
		return adsByUser;
	}
	public void setAdsByUser(Long adsByUser) {
		this.adsByUser = adsByUser;
	}
	public Long getNewsByUser() {
		return newsByUser;
	}
	public void setNewsByUser(Long newsByUser) {
		this.newsByUser = newsByUser;
	}
	
}
