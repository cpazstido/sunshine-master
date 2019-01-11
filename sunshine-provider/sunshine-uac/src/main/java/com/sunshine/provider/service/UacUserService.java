/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：UacUserService.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.sunshine.provider.service;

import com.github.pagehelper.PageInfo;
import com.sunshine.base.dto.LoginAuthDto;
import com.sunshine.core.support.IService;
import com.sunshine.provider.model.SecurityUser;
import com.sunshine.provider.model.domain.UacUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * The interface Uac user service.
 *
 * @author paascloud.net @gmail.com
 */
public interface UacUserService extends IService<UacUser> {
	/**
	 * 根据登录名查询用户信息
	 *
	 * @param loginName the login name
	 *
	 * @return the uac user
	 */
	UacUser findByLoginName(String loginName);

	/**
	 * 根据手机号查询用户信息.
	 *
	 * @param mobileNo the mobile no
	 *
	 * @return the uac user
	 */
	UacUser findByMobileNo(String mobileNo);


	/**
	 * 获得用户拥有的权限列表, 在首次验证用户对某个资源是否有权限时, 会调用此方法, 初始化用户权限
	 *
	 * @param userId the user id
	 *
	 * @return the user perms
	 */
	List<String> getUserPerms(Long userId);

	/**
	 * 更新用户信息
	 *
	 * @param uacUser the uac user
	 *
	 * @return the int
	 */
	int updateUser(UacUser uacUser);

	/**
	 * Query user list with page list.
	 *
	 * @param uacUser the uac user
	 *
	 * @return the list
	 */
	PageInfo queryUserListWithPage(UacUser uacUser);

	/**
	 * 根据用户ID删除用户.
	 *
	 * @param userId the user id
	 *
	 * @return the int
	 */
	int deleteUserById(Long userId);

	/**
	 * 根据用户ID查询用户信息.
	 *
	 * @param userId the user id
	 *
	 * @return the uac user
	 */
	UacUser findUserInfoByUserId(Long userId);

	/**
	 * 保存用户.
	 *
	 * @param user         the user
	 * @param loginAuthDto the login auth dto
	 */
	void saveUacUser(UacUser user, LoginAuthDto loginAuthDto);


	/**
	 * 根据用户ID修改用户状态.
	 *
	 * @param uacUser      the uac user
	 * @param loginAuthDto the login auth dto
	 *
	 * @return the int
	 */
	int modifyUserStatusById(UacUser uacUser, LoginAuthDto loginAuthDto);



	/**
	 * 用户绑定菜单.
	 *
	 * @param menuIdList   the menu id list
	 * @param loginAuthDto the login auth dto
	 *
	 * @return the int
	 */
	int bindUserMenus(List<Long> menuIdList, LoginAuthDto loginAuthDto);

	/**
	 * 根据用户ID查询用户信息.
	 *
	 * @param userId the user id
	 *
	 * @return the uac user
	 */
	UacUser queryByUserId(Long userId);

	/**
	 * 校验登录名是否存在.
	 *
	 * @param loginName the login name
	 *
	 * @return the boolean
	 */
	boolean checkLoginName(String loginName);

	/**
	 * 校验邮箱是否存在.
	 *
	 * @param loginName the login name
	 *
	 * @return the boolean
	 */
	boolean checkEmail(String loginName);

	/**
	 * 校验手机号是否存在.
	 *
	 * @param validValue the valid value
	 *
	 * @return the boolean
	 */
	boolean checkMobileNo(String validValue);

	/**
	 * 根据邮箱和登录名查询用户数量.
	 *
	 * @param loginName the login name
	 * @param email     the email
	 *
	 * @return the int
	 */
	int countUserByLoginNameAndEmail(String loginName, String email);

	/**
	 * 修改用户邮箱.
	 *
	 * @param email        the email
	 * @param emailCode    the email code
	 * @param loginAuthDto the login auth dto
	 */
	void modifyUserEmail(String email, String emailCode, LoginAuthDto loginAuthDto);

	/**
	 * 重置登录密码.
	 *
	 * @param userId       the user id
	 * @param loginAuthDto the login auth dto
	 */
	void resetLoginPwd(Long userId, LoginAuthDto loginAuthDto);

	/**
	 * 激活用户.
	 *
	 * @param activeUserToken the active user token
	 */
	void activeUser(String activeUserToken);

	/**
	 * 获取用户拥有的所有权限编码.
	 *
	 * @param userId the user id
	 *
	 * @return the collection
	 */
	Collection<GrantedAuthority> loadUserAuthorities(Long userId);

	/**
	 * Handler login data.
	 *
	 * @param token     the token
	 * @param principal the principal
	 * @param request   the request
	 */
	void handlerLoginData(OAuth2AccessToken token, final SecurityUser principal, final HttpServletRequest request);

	/**
	 * Find user info by login name uac user.
	 *
	 * @param loginName the login name
	 *
	 * @return the uac user
	 */
	UacUser findUserInfoByLoginName(String loginName);
}
