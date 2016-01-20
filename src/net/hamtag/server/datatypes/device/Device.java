package net.hamtag.server.datatypes.device;

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
import net.hamtag.server.datatypes.ad.AdShown;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.news.News;
import net.hamtag.server.datatypes.purchase.Purchase;
import net.hamtag.server.datatypes.withdrawal.MoneyWithdrawal;

@Entity
@Table(name = "DEVICES")
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "device")
	private Set<AdShown> adShown;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "device")
	private Set<Purchase> purchases;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "device")
	private Set<MoneyWithdrawal> moneyWithdrawal;

	@Column(name = "PHONE_NUMBER", unique = true, nullable = false)
	private String phoneNumber;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "TOKEN")
	private String token;

	@Column(name = "CHARGE",nullable = false, insertable=false, columnDefinition = "INT NOT NULL DEFAULT 0")
	private Integer charge;
	
	@JoinColumn
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "DEVICE_CATEGORY", joinColumns = {
			@JoinColumn(name = "DEVICEID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "CATEGORYID", nullable = false, updatable = false) })
	private Set<Category> categories;
	
	@JoinColumn
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "AD_LIKES", joinColumns = {
			@JoinColumn(name = "DEVICEID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ADID", nullable = false, updatable = false) })
	private Set<Ad> likedAds;
	
	
	@JoinColumn
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "NEWS_LIKES", joinColumns = {
			@JoinColumn(name = "DEVICEID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "NEWSID", nullable = false, updatable = false) })
	private Set<News> likedNews;
	
	public void addToLikes(News news){
		if(likedNews==null){
			likedNews=new HashSet<>();
			likedNews.add(news);
		}
		likedNews.add(news);
	}
	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public Set<MoneyWithdrawal> getMoneyWithdrawal() {
		return moneyWithdrawal;
	}

	public void setMoneyWithdrawal(Set<MoneyWithdrawal> moneyWithdrawal) {
		this.moneyWithdrawal = moneyWithdrawal;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<AdShown> getAdShown() {
		return adShown;
	}

	public Set<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}

	public void setAdShown(Set<AdShown> adShown) {
		this.adShown = adShown;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(Integer charge) {
		this.charge = charge;
	}

	public Set<Ad> getLikedAds() {
		return likedAds;
	}

	public void setLikedAds(Set<Ad> likedAds) {
		this.likedAds = likedAds;
	}

	public Set<News> getLikedNews() {
		return likedNews;
	}

	public void setLikedNews(Set<News> likedNews) {
		this.likedNews = likedNews;
	}
}
