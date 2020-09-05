package com.yl.opts.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yl.opts.auth.entity.SysUser;
import com.yl.opts.auth.mapper.SysUserMapper;
import com.yl.opts.auth.service.AuthSysUserService;
import com.yl.opts.common.core.constant.AccountConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author ylyang
 */
@Slf4j
@Service
public class AuthSysUserServiceImpl implements AuthSysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser passwordLogin(String account, String password) throws AccountStatusException {
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getPhone, account);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser==null){
            log.error("{}帐号在数据库中不存在", account);
            throw new UsernameNotFoundException("此账号不存在");
        }
        if (!delegatingPasswordEncoder.matches(password, sysUser.getPassword())){
            throw new BadCredentialsException("帐号或者密码错误");
        }

        if (AccountConstant.ACCOUNT_LOCK.equals(sysUser.getLockFlag())){
            throw new LockedException(account + "帐号被锁定");
        }
        if (AccountConstant.ACCOUNT_IS_DELETE.equals(sysUser.getDelFlag())){
            throw new DisabledException("帐号被禁用");
        }
        return sysUser;
    }
}
