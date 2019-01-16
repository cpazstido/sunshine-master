

package com.sunshine.provider.mapper;

import com.sunshine.core.mybatis.MyMapper;
import com.sunshine.provider.model.domain.UacRoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UacRoleUserMapper extends MyMapper<UacRoleUser> {

}