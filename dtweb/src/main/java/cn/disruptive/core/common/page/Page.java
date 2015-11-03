package cn.disruptive.core.common.page;

import java.util.List;

/**    
 * @ClassName: Page    
 * @Description: TODO    
 */
@SuppressWarnings("rawtypes")
public class Page {

	// 当前页数
	int currentPage = 1;

	// 总页数

	public int totalPages = 1;

	// 每页显示数

	int pageRecorders = 1;

	// 总数据数
	int totalRows = 0;

	// 每页的起始数
	int pageStartRow = 0;

	// 每页的终止数
	int pageEndRow;

	// 是否有下一页

	boolean hasNextPage = false;

	// 是否有前一页

	boolean hasPreviousPage = false;

	// 下一页

	int nextPage = 1;

	// 上一页

	int previousPage = 1;
	List dataList;

	/**
	 * @return the dataList
	 */
	public List getDataList() {
		return dataList;
	}

	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public Page(int totalRows, int currentPage, int pageRecorders) {
		this.totalRows = totalRows;
		this.pageRecorders = pageRecorders;
		hasPreviousPage = false;

		this.currentPage = currentPage;

		if ((totalRows % pageRecorders) == 0) {

			totalPages = totalRows / pageRecorders;

		} else {

			totalPages = totalRows / pageRecorders + 1;

		}

		if (currentPage >= totalPages) {

			hasNextPage = false;

		} else {

			hasNextPage = true;
			nextPage = currentPage + 1;
		}
		if ((currentPage - 1) > 0) {

			hasPreviousPage = true;
			previousPage = currentPage - 1;
		} else {

			hasPreviousPage = false;

		}
		if (totalRows < pageRecorders) {

			this.pageStartRow = 0;

			this.pageEndRow = totalRows;

		} else {

			this.pageStartRow =  (currentPage - 1) * pageRecorders;;

			this.pageEndRow = pageRecorders;

		}

	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		if (currentPage < 1) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
		
	}

	/**
	 * @return the hasNextPage
	 */
	public boolean isHasNextPage() {
		return hasNextPage;
	}

	/**
	 * @param hasNextPage
	 *            the hasNextPage to set
	 */
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	/**
	 * @return the hasPreviousPage
	 */
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	/**
	 * @param hasPreviousPage
	 *            the hasPreviousPage to set
	 */
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	/**
	 * @return the pageEndRow
	 */
	public int getPageEndRow() {
		return pageEndRow;
	}

	/**
	 * @param pageEndRow
	 *            the pageEndRow to set
	 */
	public void setPageEndRow(int pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	/**
	 * @return the pageRecorders
	 */
	public int getPageRecorders() {
		return pageRecorders;
	}

	/**
	 * @param pageRecorders
	 *            the pageRecorders to set
	 */
	public void setPageRecorders(int pageRecorders) {
		this.pageRecorders = pageRecorders;
	}

	/**
	 * @return the pageStartRow
	 */
	public int getPageStartRow() {
		return pageStartRow;
	}

	/**
	 * @param pageStartRow
	 *            the pageStartRow to set
	 */
	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages
	 *            the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the totalRows
	 */
	public int getTotalRows() {
		return totalRows;
	}

	/**
	 * @param totalRows
	 *            the totalRows to set
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		return nextPage;
	}

	/**
	 * @param nextPage
	 *            the nextPage to set
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * @return the previousPage
	 */
	public int getPreviousPage() {
		return previousPage;
	}

	/**
	 * @param previousPage
	 *            the previousPage to set
	 */
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public int getFirstResult() {
		return (currentPage - 1) * pageRecorders;
	}

}
