package com.yl.opts.db.online.mapper;

import com.yl.opts.db.online.entity.OptsOnlineTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单任务表 Mapper 接口
 * </p>
 *
 * @author ylyang
 * @since 2021-05-22
 */
public interface OptsOnlineTaskMapper extends BaseMapper<OptsOnlineTask> {


    /**
     * 削减人数数量
     * @param id
     * @param num
     * @return
     */
    Integer updateLimitNum(@Param("id") Integer id, @Param("num") Integer num);
}
