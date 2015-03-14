package com.cangqu.gallery.base.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListRangeObject implements Serializable{

	private List list = new ArrayList();

	private Boolean success = true;

	private Integer totalSize = 0;

	private String msg;

	private Map<String, Object> map = new HashMap<String, Object>();

	public Map<String, Object> getMap() {
		return map;
	}

	public void put(String key, Object value) {
		this.map.put(key, value);
	}

	public ListRangeObject() {
	}

	public ListRangeObject(List list) {
		this.list = list;
		if (list != null) {
			this.totalSize = list.size();
		}

	}

	public ListRangeObject(Boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

}
