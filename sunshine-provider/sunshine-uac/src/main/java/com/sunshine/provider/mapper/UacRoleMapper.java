

package com.sunshine.provider.mapper;

import com.sunshine.core.mybatis.MyMapper;
import com.sunshine.provider.model.domain.UacRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UacRoleMapper extends MyMapper<UacRole> {
}