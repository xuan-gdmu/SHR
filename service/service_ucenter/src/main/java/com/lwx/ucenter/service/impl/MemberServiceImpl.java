package com.lwx.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.common.MD5;
import com.lwx.servicebase.exceptionhandler.LWXException;
import com.lwx.ucenter.entity.Member;
import com.lwx.ucenter.entity.vo.RegisterVo;
import com.lwx.ucenter.mapper.MemberMapper;
import com.lwx.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2021-04-07
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {



    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**登陆的方法
     *
     * @param member
     * @return
     */
    @Override
    public String login(Member member) {

        //获取登录手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        //手机号和密码非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new LWXException(20001,"登录失败");
        }

        //判断手机号是否正确
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Member mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        //没有这个手机号
        if(mobileMember == null) {
            throw new LWXException(20001,"登录失败");
        }

        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new LWXException(20001,"登录失败");
        }

        //判断用户是否禁用
        if(mobileMember.getIsDisabled()) {
            throw new LWXException(20001,"登录失败");
        }

        //登录成功
        //生成token字符串，使用jwt工具类
        String jwtToken = com.lwx.common.JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }

    /**
     * @author lwx
     * @param registerVo
     * @date 2021-4-7
     */
    @Override
    public void register(RegisterVo registerVo) {
//获取注册的数据
//        String code = registerVo.getCode(); //验证码
        //手机号
        String mobile = registerVo.getMobile();
        //昵称
        String nickname = registerVo.getNickname();
        //密码
        String password = registerVo.getPassword();

        /**非空判断
         *
         */
//        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
//                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
//            throw new GuliException(20001,"注册失败");
//        }

        /**判断验证码
           获取redis验证码
        */
//        String redisCode = redisTemplate.opsForValue().get(mobile);
//        if(!code.equals(redisCode)) {
//            throw new GuliException(20001,"注册失败");
//        }

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0) {
            throw new LWXException(20001,"注册失败");

        }

        //数据添加数据库中
        Member member = new Member();
        member.setMobile(mobile);
        member.setNickname(nickname);
        //密码需要加密的
        member.setPassword(MD5.encrypt(password));
        //用户不禁用
        member.setIsDisabled(false);
        member.setAvatar("https://edu-16888.oss-cn-guangzhou.aliyuncs.com/2021/04/06/343578caa7e049498c40fbd889ddca0a111.jpg");
        baseMapper.insert(member);
    }

    @Override
    public List<Member> getMemberList(Wrapper<Member> queryWrapper, long current, long limit) {
        return null;
    }
}
