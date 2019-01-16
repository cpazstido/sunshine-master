package com.sunshine.provider.service.impl;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sunshine.base.constants.GlobalConstant;
import com.sunshine.base.dto.LoginAuthDto;
import com.sunshine.base.enums.ErrorCodeEnum;
import com.sunshine.core.support.BaseService;
import com.sunshine.provider.mapper.UacRoleMapper;
import com.sunshine.provider.model.domain.UacAction;
import com.sunshine.provider.model.domain.UacRole;
import com.sunshine.provider.model.domain.UacRoleAction;
import com.sunshine.provider.model.domain.UacRoleUser;
import com.sunshine.provider.model.domain.UacUser;
import com.sunshine.provider.service.UacRoleActionService;
import com.sunshine.provider.service.UacRoleService;
import com.sunshine.provider.service.UacRoleUserService;
import com.sunshine.provider.service.UacUserService;
import com.sunshine.utils.PublicUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class UacRoleServiceImpl extends BaseService<UacRole> implements UacRoleService {
	@Resource
	private UacRoleMapper uacRoleMapper;
	@Resource
	private UacRoleUserService uacRoleUserService;
	private UacRoleActionService uacRoleActionService;


}
