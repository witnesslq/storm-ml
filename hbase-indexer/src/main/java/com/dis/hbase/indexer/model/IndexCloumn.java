package com.dis.hbase.indexer.model;

public class IndexCloumn {
	
	private Integer id;
	private String indexCloumnName;
	private Integer indexCloumnType;
	private Integer indexCloumnStore;
	private Integer indexCloumnIndex;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIndexCloumnName() {
		return indexCloumnName;
	}
	public void setIndexCloumnName(String indexCloumnName) {
		this.indexCloumnName = indexCloumnName;
	}
	public Integer getIndexCloumnType() {
		return indexCloumnType;
	}
	public void setIndexCloumnType(Integer indexCloumnType) {
		this.indexCloumnType = indexCloumnType;
	}
	public Integer getIndexCloumnStore() {
		return indexCloumnStore;
	}
	public void setIndexCloumnStore(Integer indexCloumnStore) {
		this.indexCloumnStore = indexCloumnStore;
	}
	public Integer getIndexCloumnIndex() {
		return indexCloumnIndex;
	}
	public void setIndexCloumnIndex(Integer indexCloumnIndex) {
		this.indexCloumnIndex = indexCloumnIndex;
	}

}
