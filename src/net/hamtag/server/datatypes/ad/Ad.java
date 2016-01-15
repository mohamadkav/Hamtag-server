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

@Entity
@Table(name="ADS", indexes = {
        @Index(columnList = "CORPORATIONID", name = "corporation_index")})
public class Ad {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "price")
	private String price;
	
	@Column(name="link")
	private String link;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="location")
	private String location;
	
	@Column(name="address")
	private String address;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="publish_time")
	private Date publishTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CORPORATIONID", nullable = false)
	private Corporation corporation;
	
	@OneToMany
	(fetch = FetchType.LAZY, mappedBy = "ad")
	private Set<AdShown> adShown = new HashSet<AdShown>(0);
	
	@OneToMany
	(fetch = FetchType.LAZY, mappedBy = "ad")
	private Set<AdContent> adContents = new HashSet<AdContent>(0);
	
	@JoinColumn
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "AD_CATEGORY", joinColumns = {
			@JoinColumn(name = "ADID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "CATEGORYID", nullable = false, updatable = false) })
	private Set<Category> categories;
	
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
	public void setCorporation(Corporation corporation){
		this.corporation=corporation;
	}
	
	public Set<AdContent> getAdContents() {
		return adContents;
	}
	public void setAdContents(Set<AdContent> adContents) {
		this.adContents = adContents;
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
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
