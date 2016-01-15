package net.hamtag.server.api.response.types.ads;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdDTO {
	@JsonProperty
	private Integer id;
	
	@JsonProperty
	private String price;
	
	@JsonProperty
	private String link;
	
	@JsonProperty
	private String phone;
	
	@JsonProperty
	private String location;
	
	@JsonProperty
	private String address;
	
	@JsonProperty
	private String comments;
	
	@JsonProperty
	private String corporation;
	
	@JsonProperty
	private Long thumbnailId;
	
	@JsonProperty
	private List<Integer> contentIds;
	
	public List<Integer> getContentIds() {
		return contentIds;
	}

	public void setContentIds(List<Integer> contentIds) {
		this.contentIds = contentIds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public Long getThumbnailId() {
		return thumbnailId;
	}

	public void setThumbnailId(Long thumbnailId) {
		this.thumbnailId = thumbnailId;
	}
	
	
}
