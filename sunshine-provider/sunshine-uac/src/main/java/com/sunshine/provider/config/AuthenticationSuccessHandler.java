package com.sunshine.provider.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunshine.base.dto.LoginAuthDto;
import com.sunshine.provider.model.SecurityUser;
import com.sunshine.utils.RedisKeyUtil;
import com.sunshine.utils.RequestUtil;
import com.sunshine.utils.wrapper.WrapMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Component("authenticationSuccessHandler")
@Slf4j
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Resource
	private ObjectMapper objectMapper;
	@Resource
	private ClientDetailsService clientDetailsService;
	@Resource
	private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

	private static final String BEARER_TOKEN_TYPE = "Basic ";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	                                    Authentication authentication) throws IOException, ServletException {

		logger.info("登录成功");

		String header = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
			throw new UnapprovedClientAuthenticationException("请求头中无client信息");
		}

		String[] tokens = RequestUtil.extractAndDecodeHeader(header);
		assert tokens.length == 2;

		String clientId = tokens[0];
		String clientSecret = tokens[1];

		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

		if (clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
		} else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
			throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
		}

		TokenRequest tokenRequest = new TokenRequest(new HashMap(), clientId, clientDetails.getScope(), "custom");

		OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

		OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
		User principal = (User) authentication.getPrincipal();

        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setLoginName(principal.getUsername());

		redisTemplate.opsForValue().set(RedisKeyUtil.getAccessTokenKey(token.getValue()), loginAuthDto, 7200, TimeUnit.SECONDS);
		log.info("用户【 {} 】记录登录日志", principal.getUsername());

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write((objectMapper.writeValueAsString(WrapMapper.ok(token))));
	}

}
