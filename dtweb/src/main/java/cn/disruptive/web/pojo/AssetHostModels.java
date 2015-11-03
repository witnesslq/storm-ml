package cn.disruptive.web.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * 
 * Description：主机模型
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "asset_host_models", catalog = "ops", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class AssetHostModels implements java.io.Serializable {

	private Integer id;
	private AssetManufacturers assetManufacturers;	// 所属制造商
	private String name;							// 模型名称
	private String intro;							// 简介
	private Set<AssetHosts> assetHostses = new HashSet<AssetHosts>(0); // 包括的主机

	// Constructors

	/** default constructor */
	public AssetHostModels() {
	}

	/** full constructor */
	public AssetHostModels(AssetManufacturers assetManufacturers, String name,
			String intro, Set<AssetHosts> assetHostses) {
		this.assetManufacturers = assetManufacturers;
		this.name = name;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer_id")
	public AssetManufacturers getAssetManufacturers() {
		return this.assetManufacturers;
	}

	public void setAssetManufacturers(AssetManufacturers assetManufacturers) {
		this.assetManufacturers = assetManufacturers;
	}

	@Column(name = "name", unique = true, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "intro", length = 65535)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetHostModels")
	public Set<AssetHosts> getAssetHostses() {
		return this.assetHostses;
	}

	public void setAssetHostses(Set<AssetHosts> assetHostses) {
		this.assetHostses = assetHostses;
	}

}