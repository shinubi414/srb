package com.achao.srb.core.service.impl;

import com.achao.srb.core.pojo.entity.UserInfo;
import com.achao.srb.core.mapper.UserInfoMapper;
import com.achao.srb.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
