package com.lwx.ucenter.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.lwx.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2021-04-07
 */
public interface MemberMapper extends BaseMapper<Member> {
    List<Member> getMemberList(@Param(Constants.WRAPPER) QueryWrapper<Member> queryWrapper, @Param("current") long current, @Param("limit") long limit);

    Boolean deleteByMobile(@Param("mobile") String mobile);
}
