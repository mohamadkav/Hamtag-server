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
import net.hamtag.server.datatypes.contentprovider.ContentProvider;
import net.hamtag.server.datatypes.news.News;

@Entity
@Table(name = "CATEGORIES")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "translation")
	private String translation;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	private Set<News> news;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	private Set<Ad> ads;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
	private Set<ContentProvider> contentProviders;

	public void setAds(Set<Ad> ads) {
		this.ads = ads;
	}

	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

	public Set<Ad> getAds() {
		return ads;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public Set<ContentProvider> getContentProviders() {
		return contentProviders;
	}

	public void setContentProviders(Set<ContentProvider> contentProviders) {
		this.contentProviders = contentProviders;
	}
	
}
