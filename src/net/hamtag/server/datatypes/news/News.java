package net.hamtag.server.datatypes.news;

import java.io.File;
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

	@Column(name = "CONTENT_TYPE")
	private String contentType;

	@Column(name = "NEWSTEXT")
	private String text;

	@Column(name = "CONTENT")
	private File content;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "news")
	private Set<NewsShown> newsShowns = new HashSet<NewsShown>(0);

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public File getContent() {
		return content;
	}

	public void setContent(File content) {
		this.content = content;
	}

	public Set<NewsShown> getNewsShowns() {
		return newsShowns;
	}

	public void setNewsShowns(Set<NewsShown> newsShowns) {
		this.newsShowns = newsShowns;
	}

}
