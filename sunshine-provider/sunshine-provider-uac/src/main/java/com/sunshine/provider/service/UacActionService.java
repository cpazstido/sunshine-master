

package com.sunshine.provider.service;

import com.github.pagehelper.PageInfo;
import com.sunshine.base.dto.LoginAuthDto;
import com.sunshine.core.support.IService;
import com.sunshine.provider.model.domain.UacAction;

import java.util.List;


public interface UacActionService extends IService<UacAction> {
	/**
	 * 根据用户Id查询拥有的按钮权限.
	 *
	 * @param userId the user id
	 *
	 * @return the own uac action list
	 */
	List<UacAction> getOwnActionListByUserId(Long userId);
}
