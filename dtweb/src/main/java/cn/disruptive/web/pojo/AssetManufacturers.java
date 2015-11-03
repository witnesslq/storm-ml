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

/**
 * 
 * Description：制造商
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "asset_manufacturers", catalog = "ops")
public class AssetManufacturers implements java.io.Serializable {

	private Integer id;
	private String name;		// 制造商名字
	private String website;		// 制造商网站
	private String intro;		// 备注
	private Set<AssetHostModels> assetHostModelses = new HashSet<AssetHostModels>(0); // 包含的主机模型

	// Constructors

	/** default constructor */
	public AssetManufacturers() {
	}

	/** full constructor */
	public AssetManufacturers(String name, String website, String intro,
			Set<AssetHostModels> assetHostModelses) {
		this.name = name;
		this.website = website;
		this.intro = intro;
		this.assetHostModelses = assetHostModelses;
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

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetManufacturers")
	public Set<AssetHostModels> getAssetHostModelses() {
		return this.assetHostModelses;
	}

	public void setAssetHostModelses(Set<AssetHostModels> assetHostModelses) {
		this.assetHostModelses = assetHostModelses;
	}

}