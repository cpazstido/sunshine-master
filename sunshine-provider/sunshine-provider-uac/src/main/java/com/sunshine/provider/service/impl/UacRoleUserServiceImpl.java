package com.sunshine.provider.service.impl;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.sunshine.base.constants.GlobalConstant;
import com.sunshine.base.enums.ErrorCodeEnum;
import com.sunshine.core.support.BaseService;
import com.sunshine.provider.mapper.UacRoleUserMapper;
import com.sunshine.provider.model.domain.UacRoleUser;
import com.sunshine.provider.service.UacRoleUserService;
import com.sunshine.utils.PublicUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


@Service
@Transactional(rollbackFor = Exception.class)
public class UacRoleUserServiceImpl extends BaseService<UacRoleUser> implements UacRoleUserService {
	@Resource
	private UacRoleUserMapper uacRoleUserMapper;

}
