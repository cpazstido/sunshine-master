package com.sunshine.provider.service.impl;

import com.github.pagehelper.PageInfo;
import com.sunshine.base.dto.LoginAuthDto;
import com.sunshine.core.support.BaseService;
import com.sunshine.provider.mapper.UacUserMapper;
import com.sunshine.provider.model.SecurityUser;
import com.sunshine.provider.model.domain.UacUser;
import com.sunshine.provider.service.UacUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UacUserServiceImpl extends BaseService<UacUser> implements UacUserService {
	@Resource
	private UacUserMapper uacUserMapper;

	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public UacUser findByLoginName(String loginName) {
		logger.info("findByLoginName - 根据用户名查询用户信息. loginName={}", loginName);

		return uacUserMapper.findByLoginName(loginName);
	}

    @Override
    public UacUser findByMobileNo(String mobileNo) {
        return null;
    }

    @Override
    public List<String> getUserPerms(Long userId) {
        return null;
    }

    @Override
    public int updateUser(UacUser uacUser) {
        return 0;
    }

    @Override
    public PageInfo queryUserListWithPage(UacUser uacUser) {
        return null;
    }

    @Override
    public int deleteUserById(Long userId) {
        return 0;
    }

    @Override
    public UacUser findUserInfoByUserId(Long userId) {
        return null;
    }

    @Override
    public void saveUacUser(UacUser user, LoginAuthDto loginAuthDto) {

    }

    @Override
    public int modifyUserStatusById(UacUser uacUser, LoginAuthDto loginAuthDto) {
        return 0;
    }

    @Override
    public int bindUserMenus(List<Long> menuIdList, LoginAuthDto loginAuthDto) {
        return 0;
    }

    @Override
    public UacUser queryByUserId(Long userId) {
        return null;
    }

    @Override
    public boolean checkLoginName(String loginName) {
        return false;
    }

    @Override
    public boolean checkEmail(String loginName) {
        return false;
    }

    @Override
    public boolean checkMobileNo(String validValue) {
        return false;
    }

    @Override
    public int countUserByLoginNameAndEmail(String loginName, String email) {
        return 0;
    }

    @Override
    public void modifyUserEmail(String email, String emailCode, LoginAuthDto loginAuthDto) {

    }

    @Override
    public void resetLoginPwd(Long userId, LoginAuthDto loginAuthDto) {

    }

    @Override
    public void activeUser(String activeUserToken) {

    }

    @Override
    public Collection<GrantedAuthority> loadUserAuthorities(Long userId) {
        return null;
    }

    @Override
    public void handlerLoginData(OAuth2AccessToken token, SecurityUser principal, HttpServletRequest request) {

    }

    @Override
    public UacUser findUserInfoByLoginName(String loginName) {
        return null;
    }
}
