package net.hamtag.server.datatypes.category;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.news.News;

@Entity
@Table(name = "CATEGORIES")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", unique = true)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	private Set<News> news;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	private Set<Device> devices;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	private Set<Ad> ads;

	public void setAds(Set<Ad> ads) {
		this.ads = ads;
	}

	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Set<Ad> getAds() {
		return ads;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
