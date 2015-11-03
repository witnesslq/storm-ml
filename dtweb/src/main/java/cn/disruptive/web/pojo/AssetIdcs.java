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
 * Description：idc机房（机柜）
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "asset_idcs", catalog = "ops", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class AssetIdcs implements java.io.Serializable {

	private Integer id;
	private String name;		// 机房（机柜）名字
	private String street;		// 所在街道（廊道）
	private Set<AssetHosts> assetHostses = new HashSet<AssetHosts>(0); // 包含的主机

	// Constructors

	/** default constructor */
	public AssetIdcs() {
	}

	/** full constructor */
	public AssetIdcs(String name, String street, Set<AssetHosts> assetHostses) {
		this.name = name;
		this.street = street;
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

	@Column(name = "street")
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "assetIdcs")
	public Set<AssetHosts> getAssetHostses() {
		return this.assetHostses;
	}

	public void setAssetHostses(Set<AssetHosts> assetHostses) {
		this.assetHostses = assetHostses;
	}

}