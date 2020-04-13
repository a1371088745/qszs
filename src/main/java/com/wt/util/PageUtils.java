package com.wt.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {
	
	private int page;
	private int limit;
	private int totalPage;
	private int count;
	private List list = new ArrayList<>();
	
	public int getPage() {
		return page;
	}
	public void setPage(String currentPage) {
		if(currentPage == null) {
			this.page = 1;
		}else {
			if(Integer.parseInt(currentPage)<1) {
				this.page = 1;
			}else if(Integer.parseInt(currentPage)>totalPage) {
				this.page = this.totalPage;
			}else{
				this.page = Integer.parseInt(currentPage);
			}
		}
	}
	public  void setPage(int page){
		this.page=page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int count) {
		if(count%this.limit == 0) {
			this.totalPage = count/this.limit;
		}else {
			this.totalPage = count/this.limit+1;
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
