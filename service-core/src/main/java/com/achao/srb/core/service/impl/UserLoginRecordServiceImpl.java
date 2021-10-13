package com.achao.srb.core.service.impl;

import com.achao.srb.core.pojo.UserLoginRecord;
import com.achao.srb.core.mapper.UserLoginRecordMapper;
import com.achao.srb.core.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

}
