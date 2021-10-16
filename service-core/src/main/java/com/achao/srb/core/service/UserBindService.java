package com.achao.srb.core.service;

import com.achao.srb.core.pojo.entity.UserBind;
import com.achao.srb.core.pojo.vo.UserBindVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户绑定表 服务类
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */
public interface UserBindService extends IService<UserBind> {

    String commitBindUser(UserBindVO userBindVO, Long userId);
}
