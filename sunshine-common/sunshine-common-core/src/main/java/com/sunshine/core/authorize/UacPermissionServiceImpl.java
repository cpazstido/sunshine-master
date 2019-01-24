package com.sunshine.core.authorize;

import com.google.common.base.Joiner;
import com.sunshine.base.constants.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;


@Slf4j
@Component("permissionService")
public class UacPermissionServiceImpl implements UacPermissionService {
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	private static final String OAUTH2_CLIENT_PREFIX = "sofmit-client";

	@Override
	public boolean hasPermission(Authentication authentication, HttpServletRequest request) {
		String currentLoginName = SecurityUtils.getCurrentLoginName();
		Set<String> currentAuthorityUrl = SecurityUtils.getCurrentAuthorityUrl();
		String requestURI = request.getRequestURI();
//		log.info("验证权限loginName={}, requestURI={}, hasAuthorityUrl={}", currentLoginName, requestURI, Joiner.on(GlobalConstant.Symbol.COMMA).join(currentAuthorityUrl));
		// 超级管理员 全部都可以访问
		if (StringUtils.equals(currentLoginName, GlobalConstant.Sys.SUPER_MANAGER_LOGIN_NAME)) {
			return true;
		}

		// Feign客户端具有所有权限, 如果需要则在角色权限中控制
		if (currentLoginName.contains(OAUTH2_CLIENT_PREFIX)) {
			return true;
		}

		for (final String authority : currentAuthorityUrl) {
			if (antPathMatcher.match(authority, requestURI)) {
				return true;
			}
		}
		return false;
	}
}
