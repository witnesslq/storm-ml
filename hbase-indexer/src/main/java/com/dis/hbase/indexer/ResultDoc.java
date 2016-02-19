package com.dis.hbase.indexer;

import java.util.List;

public class ResultDoc {
	
	private Integer docTotal;
	private Integer totalHits;
	
	private List<ResultDocRow> rows;
	
	public Integer getDocTotal() {
		return docTotal;
	}
	public void setDocTotal(Integer docTotal) {
		this.docTotal = docTotal;
	}
	public Integer getTotalHits() {
		return totalHits;
	}
	public void setTotalHits(Integer totalHits) {
		this.totalHits = totalHits;
	}
	public List<ResultDocRow> getRows() {
		return rows;
	}
	public void setRows(List<ResultDocRow> rows) {
		this.rows = rows;
	}
	
	
	

}
