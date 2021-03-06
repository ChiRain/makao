package com.qcloud.component.form.web.vo;

import java.util.Date;
import java.math.BigDecimal;

public class FormTableMappingVO {
	
	//ID
	private long id;		
	
	//表单id
	private long mainFormId;		
	
	//数据库表id
	private long tableId;		

	public FormTableMappingVO(){
	
	}

	public FormTableMappingVO(long id,long mainFormId,long tableId){
		this.id = id;		
		this.mainFormId = mainFormId;		
		this.tableId = tableId;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setMainFormId(long mainFormId) {
		this.mainFormId = mainFormId;
	}

	public long getMainFormId() {
		return mainFormId;
	}	
		
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public long getTableId() {
		return tableId;
	}	
		
}
