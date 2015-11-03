package cn.disruptive.web.pojo;

import java.util.Date;
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
 * Description：告警主机
 * Data：		2015年10月22日
 */
@Entity
@Table(name = "alert_hosts", catalog = "ops", uniqueConstraints = @UniqueConstraint(columnNames = "host_id"))
public class AlertHosts implements java.io.Serializable {

	// Fields

	private Integer id;
	private AssetHosts assetHosts;
	private String serviceInstances;
	private boolean enabled;
	private Date updated;
	private Date enableAt;
	private Set<AlertHostsAccountsRelation> alertHostsAccountsRelations = new HashSet<AlertHostsAccountsRelation>(
			0);
	private Set<AlertToggleStatusNotes> alertToggleStatusNoteses = new HashSet<AlertToggleStatusNotes>(
			0);

	// Constructors

	/** default constructor */
	public AlertHosts() {
	}

	/** full constructor */
	public AlertHosts(AssetHosts assetHosts, String serviceInstances,
			boolean enabled, Date updated, Date enableAt,
			Set<AlertHostsAccountsRelation> alertHostsAccountsRelations,
			Set<AlertToggleStatusNotes> alertToggleStatusNoteses) {
		this.assetHosts = assetHosts;
		this.serviceInstances = serviceInstances;
		this.enabled = enabled;
		this.updated = updated;
		this.enableAt = enableAt;
		this.alertHostsAccountsRelations = alertHostsAccountsRelations;
		this.alertToggleStatusNoteses = alertToggleStatusNoteses;
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
	@JoinColumn(name = "host_id", unique = true)
	public AssetHosts getAssetHosts() {
		return this.assetHosts;
	}

	public void setAssetHosts(AssetHosts assetHosts) {
		this.assetHosts = assetHosts;
	}

	@Column(name = "service_instances")
	public String getServiceInstances() {
		return this.serviceInstances;
	}

	public void setServiceInstances(String serviceInstances) {
		this.serviceInstances = serviceInstances;
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

	@Column(name = "enable_at", length = 19)
	public Date getEnableAt() {
		return this.enableAt;
	}

	public void setEnableAt(Date enableAt) {
		this.enableAt = enableAt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "alertHosts")
	public Set<AlertHostsAccountsRelation> getAlertHostsAccountsRelations() {
		return this.alertHostsAccountsRelations;
	}

	public void setAlertHostsAccountsRelations(
			Set<AlertHostsAccountsRelation> alertHostsAccountsRelations) {
		this.alertHostsAccountsRelations = alertHostsAccountsRelations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "alertHosts")
	public Set<AlertToggleStatusNotes> getAlertToggleStatusNoteses() {
		return this.alertToggleStatusNoteses;
	}

	public void setAlertToggleStatusNoteses(
			Set<AlertToggleStatusNotes> alertToggleStatusNoteses) {
		this.alertToggleStatusNoteses = alertToggleStatusNoteses;
	}

}