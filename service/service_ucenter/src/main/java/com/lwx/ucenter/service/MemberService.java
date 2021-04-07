package com.lwx.ucenter.service;

import com.lwx.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author lwx
 * @since 2021-04-07
 */
public interface MemberService extends IService<Member> {

    String login(Member member);

    void register(RegisterVo registerVo);
}
