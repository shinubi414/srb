package com.achao.srb.core.service.impl;

import com.achao.srb.core.pojo.UserAccount;
import com.achao.srb.core.mapper.UserAccountMapper;
import com.achao.srb.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
