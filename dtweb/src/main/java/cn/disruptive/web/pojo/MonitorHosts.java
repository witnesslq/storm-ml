package cn.disruptive.web.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 
 * Description：监控主机
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "monitor_hosts", catalog = "ops")
public class MonitorHosts implements java.io.Serializable {

	// Fields

	private Integer id;
	private AssetHosts assetHosts;
	private boolean enabled;
	private Date updated;

	// Constructors

	/** default constructor */
	public MonitorHosts() {
	}

	/** full constructor */
	public MonitorHosts(AssetHosts assetHosts, boolean enabled, Date updated) {
		this.assetHosts = assetHosts;
		this.enabled = enabled;
		this.updated = updated;
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
	@JoinColumn(name = "host_id")
	public AssetHosts getAssetHosts() {
		return this.assetHosts;
	}

	public void setAssetHosts(AssetHosts assetHosts) {
		this.assetHosts = assetHosts;
	}

	@Column(name = "enabled")
	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "updated", length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}