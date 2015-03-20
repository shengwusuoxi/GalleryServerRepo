package com.cangqu.gallery.server.base.vo;

import java.io.Serializable;

/**
 * ext 翻页对象
 * @author fastech.wangxiaobo
 * 2010-7-25
 */
public class PageObject implements Serializable{
	//记录开始的行数
	private Integer start;
	//返回多少条数据
	private Integer limit;
	//排序的列表
	private String orderby;
	//排序类型 asc desc
	private String sort;
	
	public String toString(){
		
		return "start:"+start+",limit:"+limit+",orderby:"+orderby+",sort:"+sort;
	}
	
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	
	
}
