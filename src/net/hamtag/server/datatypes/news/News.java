package net.hamtag.server.datatypes.news;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="NEWS", indexes = {
        @Index(columnList = "CATEGORY", name = "NEWS_CATEGORY_INDEX")})
public class News {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
	private Integer id;
	
	@Column(name="CONTENT_TYPE")
	private String contentType;
	
	@Column(name="CATEGORY")
	private String category;
	
	@Column(name = "CONTENT")
	private File content;
	
	@OneToMany
	(fetch = FetchType.LAZY, mappedBy = "ad")
	private Set<NewsShown> newsShowns = new HashSet<NewsShown>(0);

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
