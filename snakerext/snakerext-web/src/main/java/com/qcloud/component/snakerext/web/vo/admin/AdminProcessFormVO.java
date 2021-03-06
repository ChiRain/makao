package com.qcloud.component.snakerext.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminProcessFormVO {
	
	//ID
	private long id;		
	
	//流程标识
	private String processId;		
	
	//主表单id
	private long mainFormId;		

	public AdminProcessFormVO(){
	
	}

	public AdminProcessFormVO(long id,String processId,long mainFormId){
		this.id = id;		
		this.processId = processId;		
		this.mainFormId = mainFormId;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessId() {
		return processId;
	}	
		
	public void setMainFormId(long mainFormId) {
		this.mainFormId = mainFormId;
	}

	public long getMainFormId() {
		return mainFormId;
	}	
		
}
