

package com.sunshine.provider.mapper;

import com.sunshine.core.mybatis.MyMapper;
import com.sunshine.provider.model.domain.UacAction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface UacActionMapper extends MyMapper<UacAction> {
	/**
	 * Gets own uac action list by user id.
	 *
	 * @param userId the user id
	 *
	 * @return the own uac action list by user id
	 */
	List<UacAction> getOwnUacActionListByUserId(Long userId);
}