package net.hamtag.server.datatypes.ad;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.hamtag.server.datatypes.corporation.Corporation;

@Entity
@Table(name="ADS")
public class Ad {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "price")
	private String price;
	
	@Column(name="link")
	private String link;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="location")
	private String location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CORPORATION_ID", nullable = false)
	private Corporation corporation;
	

	public Corporation getCorporation() {
		return this.corporation;
	}
	public void setCorporation(Corporation corporation){
		this.corporation=corporation;
	}
	
}
