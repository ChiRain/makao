package com.qcloud.component.organization.web.vo.admin;

import java.util.Date;
import java.math.BigDecimal;

public class AdminPostRoleVO {
	
	//ID
	private long id;		
	
	//角色id
	private long roleId;		
	
	//岗位id
	private long postId;		

	public AdminPostRoleVO(){
	
	}

	public AdminPostRoleVO(long id,long roleId,long postId){
		this.id = id;		
		this.roleId = roleId;		
		this.postId = postId;		
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}	
		
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getRoleId() {
		return roleId;
	}	
		
	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getPostId() {
		return postId;
	}	
		
}
