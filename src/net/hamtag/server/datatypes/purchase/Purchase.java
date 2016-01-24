package net.hamtag.server.datatypes.purchase;

import java.util.Date;

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
import javax.persistence.Table;

import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.marketcontent.MarketContent;

@Entity
@Table(name="PURCHASES", indexes = {@Index(columnList = "CONTENTID", name = "MARKET_CONTENT_PURCHASE_INDEX"),
		@Index(columnList = "DEVICEID", name = "DEVICE_ID_PURCHASE_INDEX")})
public class Purchase {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
	private Long id;
	@Column(name="PURCHASE_DATE")
	private Date purchaseDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICEID", nullable = false)
	private Device device;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTENTID", nullable = false)
	private MarketContent marketContent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public MarketContent getMarketContent() {
		return marketContent;
	}
	public void setMarketContent(MarketContent marketContent) {
		this.marketContent = marketContent;
	}
	
}
