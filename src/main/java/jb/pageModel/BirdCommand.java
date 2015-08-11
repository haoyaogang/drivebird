package jb.pageModel;

import java.util.Date;

@SuppressWarnings("serial")
public class BirdCommand implements java.io.Serializable {

	private static final long serialVersionUID = 5454155825314635342L;

	private java.lang.String id;	
	private Date addtime;			
	private java.lang.String command;	
	private java.lang.String equipType;	
	private java.lang.String remark;	

	

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
	public void setCommand(java.lang.String command) {
		this.command = command;
	}
	
	public java.lang.String getCommand() {
		return this.command;
	}
	public void setEquipType(java.lang.String equipType) {
		this.equipType = equipType;
	}
	
	public java.lang.String getEquipType() {
		return this.equipType;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}

}
