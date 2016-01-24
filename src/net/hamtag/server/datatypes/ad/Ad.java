package net.hamtag.server.datatypes.ad;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.datatypes.device.Device;

@Entity
@Table(name = "ADS", indexes = { @Index(columnList = "CORPORATIONID", name = "corporation_index") })
public class Ad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "price")
	private Integer price;

	@Column(name = "link")
	private String link;

	@Column(name = "phone")
	private String phone;

	@Column(name = "location")
	private String location;

	@Column(name = "address")
	private String address;

	@Column(name = "comments")
	private String comments;

	@Column(name = "publish_time")
	private Date publishTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CORPORATIONID", nullable = false)
	private Corporation corporation;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ad")
	private Set<AdShown> adShown;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ad")
	private Set<AdContent> adContents;

	@JoinColumn
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "AD_CATEGORY", joinColumns = {
			@JoinColumn(name = "ADID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "CATEGORYID", nullable = false, updatable = false) })
	private Set<Category> categories;

	@JoinColumn
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "AD_LIKES", joinColumns = {
			@JoinColumn(name = "ADID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "DEVICEID", nullable = false, updatable = false) })
	private Set<Device> likedByDevices;
	
	public boolean addToLikes(Device device){
		if(likedByDevices==null){
			likedByDevices=new HashSet<>();
			likedByDevices.add(device);
			return true;
		}
		if(likedByDevices.contains(device))
			return false;
		likedByDevices.add(device);
		return true;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Set<Category> getCategories() {
		return categories;
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

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<AdShown> getAdShown() {
		return adShown;
	}

	public Corporation getCorporation() {
		return this.corporation;
	}

	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}

	public Set<AdContent> getAdContents() {
		return adContents;
	}

	public void setAdContents(Set<AdContent> adContents) {
		this.adContents = adContents;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Device> getLikedByDevices() {
		return likedByDevices;
	}

	public void setLikedByDevices(Set<Device> likedByDevices) {
		this.likedByDevices = likedByDevices;
	}

	public void setAdShown(Set<AdShown> adShown) {
		this.adShown = adShown;
	}

}
