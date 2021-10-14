package com.achao.srb.core.mapper;

import com.achao.srb.core.pojo.dto.ExcelDictDTO;
import com.achao.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */
public interface DictMapper extends BaseMapper<Dict> {


    void insertBatch(List<ExcelDictDTO> list);
}
