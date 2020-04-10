package com.wt.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {
	
	private int currentPage;
	private int pageSize;
	private int totalPage;
	private int count;
	private List list = new ArrayList<>();
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		if(currentPage == null) {
			this.currentPage = 1;
		}else {
			if(Integer.parseInt(currentPage)<1) {
				this.currentPage = 1;
			}else if(Integer.parseInt(currentPage)>totalPage) {
				this.currentPage = this.totalPage;
			}else{
				this.currentPage = Integer.parseInt(currentPage);
			}
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int count) {
		if(count%this.pageSize == 0) {
			this.totalPage = count/this.pageSize;
		}else {
			this.totalPage = count/this.pageSize+1;
		}
		this.totalPage = totalPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}	
	
	
	

}
