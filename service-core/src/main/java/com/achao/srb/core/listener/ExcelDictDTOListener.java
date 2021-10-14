package com.achao.srb.core.listener;

import com.achao.srb.core.mapper.DictMapper;
import com.achao.srb.core.pojo.dto.ExcelDictDTO;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {

    private DictMapper dictMapper;
    private static final Integer BATCH_COUNT=5;
    private List<ExcelDictDTO> list = new ArrayList<>();

    public ExcelDictDTOListener(DictMapper dictMapper){
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(ExcelDictDTO data, AnalysisContext analysisContext) {
        log.info("解析到一条记录: {}", data);
        list.add(data);
        if (list.size() >= BATCH_COUNT){
            saveData();
            list.clear();
        }


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    private void saveData(){
        log.info("{}条数据，开始存储数据库！", list.size());
        dictMapper.insertBatch(list);
        log.info("{}条存储数据库成功！");
    }
}
