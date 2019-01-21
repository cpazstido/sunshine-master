

package com.sunshine.provider.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sunshine.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;


@Data
@Table(name = "uac_user")
@Alias(value = "uacUser")
public class UacUser extends BaseEntity {
	private static final long serialVersionUID = 5465775492730080699L;

	/**
	 * 登录名
	 */
	@Column(name = "login_name")
	private String loginName;

	/**
	 * 登录密码
	 */
	@Column(name = "login_pwd")
	private String loginPwd;

	/**
	 * 盐,用于shiro加密, 字段停用
	 */
	private String salt;

	/**
	 * 工号
	 */
	@Column(name = "user_code")
	private String userCode;

	/**
	 * 姓名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 手机号
	 */
	@Column(name = "mobile_no")
	private String mobileNo;

	/**
	 * 状态
	 */
	private String status;

	private String email;

	/**
	 * 用户来源
	 */
	@Column(name = "user_source")
	private String userSource;

	/**
	 * 操作员类型（2000伙伴, 3000客户, 1000运营）
	 */
	private String type;

	/**
	 * 最后登录IP地址
	 */
	@Column(name = "last_login_ip")
	private String lastLoginIp;

	/**
	 * 最后登录位置
	 */
	@Column(name = "last_login_location")
	private String lastLoginLocation;

	/**
	 * 描述
	 */
	private String remark;

	/**
	 * 最后登录时间
	 */
	@Column(name = "last_login_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date lastLoginTime;

	/**
	 * 是否更改过密码
	 */
	@Column(name = "is_changed_pwd")
	private Short isChangedPwd;

	/**
	 * 连续输错密码次数（连续5次输错就冻结帐号）
	 */
	@Column(name = "pwd_error_count")
	private Short pwdErrorCount;

	/**
	 * 最后输错密码时间
	 */
	@Column(name = "pwd_error_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date pwdErrorTime;

	/**
	 * 用户所属的组织ID
	 */
	@Transient
	private Long groupId;

	@Transient
	private String groupName;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserSource() {
		return userSource;
	}

	public void setUserSource(String userSource) {
		this.userSource = userSource;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginLocation() {
		return lastLoginLocation;
	}

	public void setLastLoginLocation(String lastLoginLocation) {
		this.lastLoginLocation = lastLoginLocation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Short getIsChangedPwd() {
		return isChangedPwd;
	}

	public void setIsChangedPwd(Short isChangedPwd) {
		this.isChangedPwd = isChangedPwd;
	}

	public Short getPwdErrorCount() {
		return pwdErrorCount;
	}

	public void setPwdErrorCount(Short pwdErrorCount) {
		this.pwdErrorCount = pwdErrorCount;
	}

	public Date getPwdErrorTime() {
		return pwdErrorTime;
	}

	public void setPwdErrorTime(Date pwdErrorTime) {
		this.pwdErrorTime = pwdErrorTime;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}