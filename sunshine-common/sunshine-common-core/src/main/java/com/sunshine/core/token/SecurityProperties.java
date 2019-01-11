package com.sunshine.core.token;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "sunshine.security")
public class SecurityProperties {

	/**
	 * OAuth2认证服务器配置
	 */
	private OAuth2Properties oauth2 = new OAuth2Properties();


	/**
	 * Gets oauth 2.
	 *
	 * @return the oauth 2
	 */
	public OAuth2Properties getOauth2() {
		return oauth2;
	}

	/**
	 * Sets oauth 2.
	 *
	 * @param oauth2 the oauth 2
	 */
	public void setOauth2(OAuth2Properties oauth2) {
		this.oauth2 = oauth2;
	}

}

