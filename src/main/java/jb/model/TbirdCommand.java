
/*
 * @author John
 * @date - 2015-08-11
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "bird_command")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TbirdCommand implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "BirdCommand";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ADDTIME = "addtime";
	public static final String ALIAS_COMMAND = "指令";
	public static final String ALIAS_NAME = "名称";
	public static final String ALIAS_EQUIP_TYPE = "设备分类";
	public static final String ALIAS_REMARK = "备注";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//
	private java.util.Date addtime;
	//@Length(max=36)
	private java.lang.String command;
	
	private java.lang.String name;
	//@Length(max=36)
	private java.lang.String equipType;
	

	//@Length(max=256)
	private java.lang.String remark;
	//columns END


		public TbirdCommand(){
		}
		public TbirdCommand(String id) {
			this.id = id;
		}
	

	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public java.lang.String getId() {
		return this.id;
	}
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	@Column(name = "addtime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getAddtime() {
		return this.addtime;
	}
	
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}
	
	@Column(name = "command", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getCommand() {
		return this.command;
	}
	
	public void setCommand(java.lang.String command) {
		this.command = command;
	}
	
	@Column(name = "equip_type", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public java.lang.String getEquipType() {
		return this.equipType;
	}
	
	public void setEquipType(java.lang.String equipType) {
		this.equipType = equipType;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Addtime",getAddtime())
			.append("Command",getCommand())
			.append("EquipType",getEquipType())
			.append("Remark",getRemark())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BirdCommand == false) return false;
		if(this == obj) return true;
		BirdCommand other = (BirdCommand)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

