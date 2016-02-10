package net.hamtag.server.api.response.types.ads;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.hamtag.server.api.response.types.content.ContentDTO;

public class AdDTO {
	@JsonProperty
	private Long id;
	
	@JsonProperty
	private String price;
	
	@JsonProperty
	private String link;
	
	@JsonProperty
	private String phone;
	
	@JsonProperty
	private String description;
	
	@JsonProperty
	private String title;
	
	@JsonProperty
	private String location;
	
	@JsonProperty
	private String address;
	
	@JsonProperty
	private String comments;
	
	@JsonProperty
	private String corporation;
	
	@JsonProperty
	private List<ContentDTO> contentInfos;
	
	@JsonProperty
	private List<String> categories;

	public List<ContentDTO> getContentInfos() {
		return contentInfos;
	}

	public void setContentInfos(List<ContentDTO> contentInfos) {
		this.contentInfos = contentInfos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	
	
	
	
}
