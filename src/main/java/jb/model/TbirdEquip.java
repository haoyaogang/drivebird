
/*
 * @author John
 * @date - 2015-08-14
 */

package jb.model;

import javax.persistence.*;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@SuppressWarnings("serial")
@Entity
@Table(name = "bird_equip")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TbirdEquip implements java.io.Serializable,IEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "BirdEquip";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ADDTIME = "addtime";
	public static final String ALIAS_NAME = "设备名称";
	public static final String ALIAS_STATUS = "状态";
	public static final String ALIAS_EQUIP_TYPE = "设备分类";
	public static final String ALIAS_GROUP_TYPE = "组";
	public static final String ALIAS_DTUTYPE = "设备型号";
	public static final String ALIAS_PWD = "密码";
	public static final String ALIAS_LOCATION = "位置";
	public static final String ALIAS_VOICE = "音量";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_CHANGETIME = "状态变更时间";
	public static final String ALIAS_UPDATETIME = "updatetime";
	
	//date formats
	public static final String FORMAT_ADDTIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_CHANGETIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	public static final String FORMAT_UPDATETIME = jb.util.Constants.DATE_FORMAT_FOR_ENTITY;
	

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	//@Length(max=36)
	private java.lang.String id;
	//
	private java.util.Date addtime;
	//@Length(max=72)
	private java.lang.String name;
	//@Length(max=4)
	private java.lang.String status;
	//@Length(max=4)
	private java.lang.String equipType;
	//@Length(max=4)
	private java.lang.String groupType;
	//@Length(max=32)
	private java.lang.String dtutype;
	//@Length(max=32)
	private java.lang.String pwd;
	//@Length(max=72)
	private java.lang.String location;
	private java.lang.String voice;
	//@Length(max=256)
	private java.lang.String remark;
	//
	private java.util.Date changetime;
	//
	private java.util.Date updatetime;
	//columns END


		public TbirdEquip(){
		}
		public TbirdEquip(String id) {
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
	

	@Column(name = "addtime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getAddtime() {
		return this.addtime;
	}
	
	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}
	
	@Column(name = "name", unique = false, nullable = true, insertable = true, updatable = true, length = 72)
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	@Column(name = "status", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	
	@Column(name = "equip_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getEquipType() {
		return this.equipType;
	}
	
	public void setEquipType(java.lang.String equipType) {
		this.equipType = equipType;
	}
	
	@Column(name = "group_type", unique = false, nullable = true, insertable = true, updatable = true, length = 4)
	public java.lang.String getGroupType() {
		return this.groupType;
	}
	
	public void setGroupType(java.lang.String groupType) {
		this.groupType = groupType;
	}
	
	@Column(name = "dtutype", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getDtutype() {
		return this.dtutype;
	}
	
	public void setDtutype(java.lang.String dtutype) {
		this.dtutype = dtutype;
	}
	
	@Column(name = "pwd", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
	public java.lang.String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(java.lang.String pwd) {
		this.pwd = pwd;
	}
	
	@Column(name = "location", unique = false, nullable = true, insertable = true, updatable = true, length = 72)
	public java.lang.String getLocation() {
		return this.location;
	}
	
	public void setLocation(java.lang.String location) {
		this.location = location;
	}
	
	@Column(name = "remark", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public java.lang.String getRemark() {
		return this.remark;
	}
	
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	

	@Column(name = "changetime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getChangetime() {
		return this.changetime;
	}
	
	public void setChangetime(java.util.Date changetime) {
		this.changetime = changetime;
	}
	

	@Column(name = "updatetime", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "voice", unique = false, nullable = true, insertable = true, updatable = true, length = 36)
	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}
	
	/*
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Addtime",getAddtime())
			.append("Name",getName())
			.append("Status",getStatus())
			.append("EquipType",getEquipType())
			.append("GroupType",getGroupType())
			.append("Dtutype",getDtutype())
			.append("Pwd",getPwd())
			.append("Location",getLocation())
			.append("Remark",getRemark())
			.append("Changetime",getChangetime())
			.append("Updatetime",getUpdatetime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BirdEquip == false) return false;
		if(this == obj) return true;
		BirdEquip other = (BirdEquip)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}*/
}

