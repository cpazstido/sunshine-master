package com.sunshine.provider.service.impl;

import com.sunshine.base.enums.ErrorCodeEnum;
import com.sunshine.core.support.BaseService;
import com.sunshine.provider.mapper.UacRoleActionMapper;
import com.sunshine.provider.model.domain.UacRoleAction;
import com.sunshine.provider.service.UacRoleActionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;


@Service
@Transactional(rollbackFor = Exception.class)
public class UacRoleActionServiceImpl extends BaseService<UacRoleAction> implements UacRoleActionService {
	@Resource
	private UacRoleActionMapper uacRoleActionMapper;

}
