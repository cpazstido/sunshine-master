

package com.sunshine.provider.service;

import com.github.pagehelper.PageInfo;
import com.sunshine.base.dto.LoginAuthDto;
import com.sunshine.base.dto.UserTokenDto;
import com.sunshine.core.support.IService;
import com.sunshine.provider.model.domain.UacUserToken;

import javax.servlet.http.HttpServletRequest;


public interface UacUserTokenService extends IService<UacUserToken> {
	/**
	 * 保存token.
	 *
	 * @param accessToken  the access token
	 * @param refreshToken the refresh token
	 * @param loginAuthDto the login auth dto
	 * @param request      the request
	 */
	void saveUserToken(String accessToken, String refreshToken, LoginAuthDto loginAuthDto, HttpServletRequest request);

	/**
	 * 获取token.
	 *
	 * @param accessToken the access token
	 *
	 * @return the by access token
	 */
	UserTokenDto getByAccessToken(String accessToken);


	/**
	 * 更新token.
	 *
	 * @param tokenDto     the token dto
	 * @param loginAuthDto the login auth dto
	 */
	void updateUacUserToken(UserTokenDto tokenDto, LoginAuthDto loginAuthDto);

    /**
     * 删除redis中用户token
     * @param tokenDto
     */
	void deleteUacUserToken(UserTokenDto tokenDto);


	/**
	 * 更新token离线状态.
	 *
	 * @return the int
	 */
	int batchUpdateTokenOffLine();

}
