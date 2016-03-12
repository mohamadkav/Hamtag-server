package net.hamtag.server.api.request.types.ads.panel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdInputRequestJson {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("categories")
	private List<String>categories;
	@JsonProperty("title")
	private String title;
	@JsonProperty("text")
	private String text;
	@JsonProperty("address")
	private String address;
	@JsonProperty("comments")
	private String comments;
	@JsonProperty("link")
	private String link;
	@JsonProperty("location")
	private String location;
	@JsonProperty("phone-number")
	private String phoneNumber;
	@JsonProperty("price")
	private Integer price;
	@JsonProperty("corporation-id")
	private Long corporationId;
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Long getCorporationId() {
		return corporationId;
	}
	public void setCorporationId(Long corporationId) {
		this.corporationId = corporationId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
}
