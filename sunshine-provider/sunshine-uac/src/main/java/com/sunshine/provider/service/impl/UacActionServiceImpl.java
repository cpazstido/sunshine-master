package com.sunshine.provider.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.sunshine.base.constants.GlobalConstant;
import com.sunshine.base.dto.LoginAuthDto;
import com.sunshine.base.enums.ErrorCodeEnum;
import com.sunshine.core.support.BaseService;
import com.sunshine.provider.mapper.UacActionMapper;
import com.sunshine.provider.mapper.UacRoleActionMapper;
import com.sunshine.provider.model.domain.UacAction;
import com.sunshine.provider.service.UacActionService;
import com.sunshine.utils.PublicUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


@Service
@Transactional(rollbackFor = Exception.class)
public class UacActionServiceImpl extends BaseService<UacAction> implements UacActionService {
	@Resource
	private UacActionMapper uacActionMapper;
	@Resource
	private UacRoleActionMapper uacRoleActionMapper;
	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public List<UacAction> getOwnActionListByUserId(Long userId) {
		if (userId == null) {
			throw new RuntimeException(ErrorCodeEnum.UAC10011001.msg());
		}
		List<UacAction> uacActionList;
		if (Objects.equals(userId, GlobalConstant.Sys.SUPER_MANAGER_USER_ID)) {
			// 获取全部权限信息
			uacActionList = uacActionMapper.selectAll();
		} else {
			uacActionList = uacActionMapper.getOwnUacActionListByUserId(userId);
		}
		return uacActionList;
	}
}
