package com.dis.hbase.indexer.model;

import java.util.Date;

public class IndexFiles {
	
	private Integer id;
	private Integer indexerId;
	private String path;
	private Date createDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIndexerId() {
		return indexerId;
	}
	public void setIndexerId(Integer indexerId) {
		this.indexerId = indexerId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
