package net.hamtag.server.datatypes.contentprovider;

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
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.user.User;

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
	
	@JoinColumn
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "CONTENT_PROVIDER_CATEGORY", joinColumns = {
			@JoinColumn(name = "CONTENTPROVIDERID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "CATEGORYID", nullable = false, updatable = false) })
	private Set<Category> categories;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "visibleProviders")
	private Set<User>shownToWhichUsers;

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

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<User> getShownToWhichUsers() {
		return shownToWhichUsers;
	}

	public void setShownToWhichUsers(Set<User> shownToWhichUsers) {
		this.shownToWhichUsers = shownToWhichUsers;
	}
	
}
