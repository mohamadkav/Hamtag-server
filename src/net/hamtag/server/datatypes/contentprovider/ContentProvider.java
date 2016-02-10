package net.hamtag.server.datatypes.contentprovider;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.hamtag.server.datatypes.news.News;

@Entity
@Table(name = "CONTENT_PROVIDERS")
public class ContentProvider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<News> newsFromContentProvider;

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

	public Set<News> getNewsFromContentProvider() {
		return newsFromContentProvider;
	}

	public void setNewsFromContentProvider(Set<News> newsFromContentProvider) {
		this.newsFromContentProvider = newsFromContentProvider;
	}
}
