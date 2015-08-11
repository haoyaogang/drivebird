package jb.pageModel;

import java.util.Date;

import jb.listener.Application;

@SuppressWarnings("serial")
public class BirdEquip implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private Date addtime;			
	private java.lang.String name;	
	private java.lang.String status;	
	private java.lang.String equipType;	
	private java.lang.String group;	
	private java.lang.String location;	
	private java.lang.String remark;	
	private Date changetime;			
	private Date updatetime;			

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	public Date getAddtime() {
		return this.addtime;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public java.lang.String getStatusZh() {
		return Application.getString(this.status);
	}
	public void setEquipType(java.lang.String equipType) {
		this.equipType = equipType;
	}
	
	public java.lang.String getEquipType() {
		return this.equipType;
	}
	public java.lang.String getEquipTypeZh() {		
		return Application.getString(this.equipType);
	}
	public void setGroup(java.lang.String group) {
		this.group = group;
	}
	
	public java.lang.String getGroupZh() {
		return Application.getString(this.group);
	}
	public void setLocation(java.lang.String location) {
		this.location = location;
	}
	
	public java.lang.String getLocation() {
		return this.location;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setChangetime(Date changetime) {
		this.changetime = changetime;
	}
	
	public Date getChangetime() {
		return this.changetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	public Date getUpdatetime() {
		return this.updatetime;
	}

}
