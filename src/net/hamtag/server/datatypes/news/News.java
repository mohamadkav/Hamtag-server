package net.hamtag.server.datatypes.news;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.hamtag.server.datatypes.category.Category;

@Entity
@Table(name = "NEWS")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "NEWSTEXT")
	private String text;

	@Column(name = "TITLE")
	private String title;
	
	@Column(name="PUBLISH_TIME")
	private Date publishTime;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "news")
	private Set<NewsShown> newsShowns = new HashSet<NewsShown>(0);
	
	@OneToMany
	(fetch = FetchType.LAZY, mappedBy = "news")
	private Set<NewsContent> newsContents = new HashSet<NewsContent>(0);

	@JoinColumn
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "NEWS_CATEGORY", joinColumns = {
			@JoinColumn(name = "NEWSID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "CATEGORYID", nullable = false, updatable = false) })
	private Set<Category> categories;

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Set<NewsContent> getNewsContents() {
		return newsContents;
	}

	public void setNewsContents(Set<NewsContent> newsContents) {
		this.newsContents = newsContents;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<NewsShown> getNewsShowns() {
		return newsShowns;
	}

	public void setNewsShowns(Set<NewsShown> newsShowns) {
		this.newsShowns = newsShowns;
	}

}
