package net.hamtag.server.api.response.types.device;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceInfoDTO {
	@JsonProperty
	private String name;
	
	@JsonProperty
	private Integer charge;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(Integer charge) {
		this.charge = charge;
	}
}
