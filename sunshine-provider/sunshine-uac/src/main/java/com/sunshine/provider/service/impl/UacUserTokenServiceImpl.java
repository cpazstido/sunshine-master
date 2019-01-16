package com.sunshine.provider.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sunshine.base.dto.LoginAuthDto;
import com.sunshine.base.dto.UserTokenDto;
import com.sunshine.core.support.BaseService;
import com.sunshine.core.token.OAuth2ClientProperties;
import com.sunshine.core.token.SecurityProperties;
import com.sunshine.provider.model.domain.UacUser;
import com.sunshine.provider.model.domain.UacUserToken;
import com.sunshine.provider.service.UacRoleService;
import com.sunshine.provider.service.UacUserService;
import com.sunshine.provider.service.UacUserTokenService;
import com.sunshine.utils.PublicUtil;
import com.sunshine.utils.RedisKeyUtil;
import com.sunshine.utils.RequestUtil;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
@Transactional(rollbackFor = Exception.class)
public class UacUserTokenServiceImpl extends BaseService<UacUserToken> implements UacUserTokenService {
	@Resource
	private UacUserService uacUserService;
	@Autowired
	private SecurityProperties securityProperties;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
    private UacRoleService uacRoleService;

	@Override
	public void saveUserToken(String accessToken, String refreshToken, LoginAuthDto loginAuthDto, HttpServletRequest request) {
        // 获取登录时间
        Long userId = loginAuthDto.getUserId();
        UacUser uacUser = uacUserService.selectByKey(userId);
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        //获取客户端操作系统
        final String os = userAgent.getOperatingSystem().getName();
        //获取客户端浏览器
        final String browser = userAgent.getBrowser().getName();
        final String remoteAddr = RequestUtil.getRemoteAddr(request);

        UacUserToken uacUserToken = new UacUserToken();
        OAuth2ClientProperties[] clients = securityProperties.getOauth2().getClients();
        int accessTokenValidateSeconds = clients[0].getAccessTokenValidateSeconds();
        int refreshTokenValiditySeconds = clients[0].getRefreshTokenValiditySeconds();
        uacUserToken.setOs(os);
        uacUserToken.setBrowser(browser);
        uacUserToken.setAccessToken(accessToken);
        uacUserToken.setAccessTokenValidity(accessTokenValidateSeconds);
        uacUserToken.setLoginIp(remoteAddr);
        uacUserToken.setLoginLocation("");
        uacUserToken.setLoginTime(uacUser.getLastLoginTime());
        uacUserToken.setLoginName(loginAuthDto.getLoginName());
        uacUserToken.setRefreshToken(refreshToken);
        uacUserToken.setRefreshTokenValidity(refreshTokenValiditySeconds);
        uacUserToken.setStatus(1);
        uacUserToken.setUserId(userId);
        uacUserToken.setUserName(loginAuthDto.getUserName());
        uacUserToken.setUpdateInfo(loginAuthDto);
        uacUserToken.setGroupId(loginAuthDto.getGroupId());
        uacUserToken.setGroupName(loginAuthDto.getGroupName());
        uacUserToken.setId(generateId());

        UserTokenDto userTokenDto = new ModelMapper().map(uacUserToken, UserTokenDto.class);

        // 存入redis数据库
        updateRedisUserToken(accessToken, accessTokenValidateSeconds, userTokenDto);
	}

    private void updateRedisUserToken(String accessToken, int accessTokenValidateSeconds, UserTokenDto userTokenDto) {
        redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(accessToken).toLowerCase(), userTokenDto, accessTokenValidateSeconds, TimeUnit.SECONDS);
    }

	@Override
	public UserTokenDto getByAccessToken(String accessToken) {
		return null;
	}

	@Override
	public void updateUacUserToken(UserTokenDto tokenDto, LoginAuthDto loginAuthDto) {

	}

	@Override
	public void deleteUacUserToken(UserTokenDto tokenDto) {

	}

	@Override
	public int batchUpdateTokenOffLine() {
		return 0;
	}
}
