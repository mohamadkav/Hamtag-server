package net.hamtag.server.datatypes.news;

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

@Entity
@Table(name="NEWS_DISPLAY", indexes = {@Index(columnList = "NEWSID", name = "NEWS_INDEX"),
		@Index(columnList = "SHOWDATE", name = "NEWS_SHOWN_DATE"),
		@Index(columnList = "DEVICEID", name = "NEWS_SHOWN_DEVICE_INDEX")})
public class NewsShown {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
	private Integer id;
	
	@Column(name = "SHOWN_TIME")
	private Integer shownTime;
	
	@Column(name="SHOWDATE")
	private Date showDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICEID", nullable = false)
	private Device device;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NEWSID", nullable = false)
	private News news;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShownTime() {
		return shownTime;
	}

	public void setShownTime(Integer shownTime) {
		this.shownTime = shownTime;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
	
	
}
