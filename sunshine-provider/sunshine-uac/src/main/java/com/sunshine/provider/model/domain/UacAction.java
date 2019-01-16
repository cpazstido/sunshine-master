

package com.sunshine.provider.model.domain;

import com.sunshine.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "uac_action")
@Alias(value = "uacAction")
public class UacAction extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 6943147531573339665L;

	/**
	 * 资源路径
	 */
	private String url;

	/**
	 * 权限名称
	 */
	@Column(name = "action_name")
	private String actionName;

	/**
	 * 权限编码
	 */
	@Column(name = "action_code")
	private String actionCode;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 菜单ID
	 */
	@Column(name = "menu_id")
	private Long menuId;

	/**
	 * 菜单ID
	 */
	@Transient
	private List<Long> menuIdList;
}