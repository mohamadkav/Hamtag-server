package net.hamtag.server.datatypes.user;

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

import net.hamtag.server.datatypes.ad.Ad;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<UserRole> userRoles;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Ad> adsMadeByUser;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<News> newsMadeByUser;
	
	
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
	
	
}
