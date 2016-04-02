package com.dis.hbase.indexer.model;

public class Indexer {
	
	private Integer id;
	private String indexName;
	private Integer indexFileQuartzType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public Integer getIndexFileQuartzType() {
		return indexFileQuartzType;
	}
	public void setIndexFileQuartzType(Integer indexFileQuartzType) {
		this.indexFileQuartzType = indexFileQuartzType;
	}
	

}
