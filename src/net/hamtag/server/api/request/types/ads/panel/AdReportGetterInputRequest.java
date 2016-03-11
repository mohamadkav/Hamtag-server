package net.hamtag.server.api.request.types.ads.panel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdReportGetterInputRequest {
	@JsonProperty("from-date")
	private Long fromDate;
	@JsonProperty("to-date")
	private Long toDate;
	@JsonProperty("page-results-from-shown-date")
	private Long startFromShownDate;
	@JsonProperty("max-results")
	private Integer maxResults;
	@JsonProperty("corporation-ids")
	private List<Long>corporationIds;
	public Long getFromDate() {
		return fromDate;
	}
	public void setFromDate(Long fromDate) {
		this.fromDate = fromDate;
	}
	public Long getToDate() {
		return toDate;
	}
	public void setToDate(Long toDate) {
		this.toDate = toDate;
	}
	public Long getStartFromShownDate() {
		return startFromShownDate;
	}
	public void setStartFromShownDate(Long startFromShownDate) {
		this.startFromShownDate = startFromShownDate;
	}
	public Integer getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	public List<Long> getCorporationIds() {
		return corporationIds;
	}
	public void setCorporationIds(List<Long> corporationIds) {
		this.corporationIds = corporationIds;
	}
}
