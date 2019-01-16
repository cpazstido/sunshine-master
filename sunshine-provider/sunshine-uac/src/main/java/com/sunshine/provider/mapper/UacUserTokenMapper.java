

package com.sunshine.provider.mapper;

import com.sunshine.core.mybatis.MyMapper;
import com.sunshine.provider.model.domain.UacUserToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Mapper
@Component
public interface UacUserTokenMapper extends MyMapper<UacUserToken> {
}