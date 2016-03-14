package net.hamtag.server.api.response.types.ads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountShownDTO {
	@JsonProperty("days-before")
	private Integer daysBefore;
	@JsonProperty("count")
	private Long count;
	public Integer getDaysBefore() {
		return daysBefore;
	}
	public void setDaysBefore(Integer daysBefore) {
		this.daysBefore = daysBefore;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
}
