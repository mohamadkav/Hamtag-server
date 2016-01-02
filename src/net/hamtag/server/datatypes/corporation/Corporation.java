package net.hamtag.server.datatypes.corporation;

import java.util.HashSet;
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

@Entity
@Table(name="CORPORATIONS")
public class Corporation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany
	(fetch = FetchType.LAZY, mappedBy = "corporation")
	private Set<Ad> ads = new HashSet<Ad>(0);
	

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
	
}
