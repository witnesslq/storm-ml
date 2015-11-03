package cn.disruptive.web.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * 
 * Description：供应商
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "asset_vendors", catalog = "ops", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class AssetVendors implements java.io.Serializable {
	
	private Integer id;
	private String name;		// 名称
	private String contact;		// 联系人
	private String cellPhone;	// 座机
	private String telphone;	// 手机
	private String website;		// 网站
	private String intro;		// 简介
	private Set<AssetHosts> assetHostses = new HashSet<AssetHosts>(0);	// 包含的主机

	// Constructors

	/** default constructor */
	public AssetVendors() {
	}

	/** full constructor */
	public AssetVendors(String name, String contact, String cellPhone,
			String telphone, String website, String intro,
			Set<AssetHosts> assetHostses) {
		this.name = name;
		this.contact = contact;
		this.cellPhone = cellPhone;
		this.telphone = telphone;
		this.website = website;
		this.intro = intro;
		this.assetHostses = assetHostses;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", unique = true, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "contact", length = 30)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "cell_phone", length = 20)
	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	@Column(name = "telphone", length = 20)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "website")
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "intro", length = 65535)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetVendors")
	public Set<AssetHosts> getAssetHostses() {
		return this.assetHostses;
	}

	public void setAssetHostses(Set<AssetHosts> assetHostses) {
		this.assetHostses = assetHostses;
	}

}