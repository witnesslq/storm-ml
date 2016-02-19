package com.dis.hbase.indexer;

public class ResultDocRow {
	
	private Double docScore;
	private Integer docId;
	private String rowKey;
	public Double getDocScore() {
		return docScore;
	}
	public void setDocScore(Double docScore) {
		this.docScore = docScore;
	}
	public Integer getDocId() {
		return docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}
	public String getRowKey() {
		return rowKey;
	}
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	

}
