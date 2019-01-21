package com.sunshine.provider.mapper;

import com.sunshine.core.mybatis.MyMapper;
import com.sunshine.provider.model.domain.UacUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface UacUserMapper extends MyMapper<UacUser> {

	/**
	 * Find by login name uac user.
	 *
	 * @param loginName the login name
	 *
	 * @return the uac user
	 */
	UacUser findByLoginName(String loginName);


}