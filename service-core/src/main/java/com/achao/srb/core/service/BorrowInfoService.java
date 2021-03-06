package com.achao.srb.core.service;

import com.achao.srb.core.pojo.entity.BorrowInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 借款信息表 服务类
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */
public interface BorrowInfoService extends IService<BorrowInfo> {

    BigDecimal getBorrowAmount(Long userId);
}
