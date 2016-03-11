package net.hamtag.server.datatypes.corporation;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.user.User;

@Entity
@Table(name = "CORPORATIONS")
public class Corporation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "corporation")
	private Set<Ad> ads;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "visibleCorporations")
	private Set<User>shownToWhichUsers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Ad> getAds() {
		return this.ads;
	}

	public void setAds(Set<Ad> ads) {
		this.ads = ads;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getShownToWhichUsers() {
		return shownToWhichUsers;
	}

	public void setShownToWhichUsers(Set<User> shownToWhichUsers) {
		this.shownToWhichUsers = shownToWhichUsers;
	}
}
