package com.yl.opts.db.online.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单提交字段
 * </p>
 *
 * @author ylyang
 * @since 2021-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OptsOnlineOrderFiledDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 提交字段的key
     */
    private String filedKey;

    /**
     * 提交的值
     */
    private String filedValue;

    /**
     * 状态 0审核中, 1成功，2失败，3撤销
     */
    private Integer status;


}
