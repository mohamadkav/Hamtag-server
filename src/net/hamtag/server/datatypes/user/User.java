package net.hamtag.server.datatypes.user;

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

import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.contentprovider.ContentProvider;
import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.datatypes.news.News;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "USERNAME")
	private String username;
	
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ENABLED")
	private Boolean enabled;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> userRoles;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Ad> adsMadeByUser;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<News> newsMadeByUser;
	
	@JoinColumn
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_VISIBLE_CONTENT_PROVIDERS", joinColumns = {
			@JoinColumn(name = "USERID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "PROVIDERID", nullable = false, updatable = false) })
	private Set<ContentProvider> visibleProviders;
	
	@JoinColumn
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_VISIBLE_CORPORATIONS", joinColumns = {
			@JoinColumn(name = "USERID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "CORPORATIONID", nullable = false, updatable = false) })
	private Set<Corporation> visibleCorporations;
	
	public boolean addNewProvider(ContentProvider contentProvider){
		if(visibleProviders==null){
			visibleProviders=new HashSet<>();
			visibleProviders.add(contentProvider);
			return true;
		}
		else
			visibleProviders.add(contentProvider);
		return true;
	}
	
	public boolean addNewCorporation(Corporation corporation){
		if(visibleCorporations==null){
			visibleCorporations=new HashSet<>();
			visibleCorporations.add(corporation);
			return true;
		}
		else
			visibleCorporations.add(corporation);
		return true;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Set<Ad> getAdsMadeByUser() {
		return adsMadeByUser;
	}
	public void setAdsMadeByUser(Set<Ad> adsMadeByUser) {
		this.adsMadeByUser = adsMadeByUser;
	}
	public Set<News> getNewsMadeByUser() {
		return newsMadeByUser;
	}
	public void setNewsMadeByUser(Set<News> newsMadeByUser) {
		this.newsMadeByUser = newsMadeByUser;
	}
	public Set<ContentProvider> getVisibleProviders() {
		return visibleProviders;
	}
	public void setVisibleProviders(Set<ContentProvider> visibleProviders) {
		this.visibleProviders = visibleProviders;
	}

	public Set<Corporation> getVisibleCorporations() {
		return visibleCorporations;
	}

	public void setVisibleCorporations(Set<Corporation> visibleCorporations) {
		this.visibleCorporations = visibleCorporations;
	}
	
}
