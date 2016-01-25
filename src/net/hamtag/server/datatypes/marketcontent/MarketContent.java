package net.hamtag.server.datatypes.marketcontent;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.hamtag.server.datatypes.corporation.DealerCorporation;
import net.hamtag.server.datatypes.purchase.Purchase;

@Entity
@Table(name = "MARKET_CONTENTS", indexes = {
		@Index(columnList = "DEALERCORPORATIONID", name = "DEALER_CORPORATION_INDEX") })
public class MarketContent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "price")
	private String price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEALERCORPORATIONID", nullable = false)
	private DealerCorporation dealerCorporation;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "marketContent")
	private Set<Purchase> purchases;

	@Column(name = "CONTENT")
	private byte[] content;

	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public DealerCorporation getDealerCorporation() {
		return dealerCorporation;
	}

	public void setDealerCorporation(DealerCorporation dealerCorporation) {
		this.dealerCorporation = dealerCorporation;
	}

}
