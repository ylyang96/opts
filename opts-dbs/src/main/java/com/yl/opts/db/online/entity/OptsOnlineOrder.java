package com.yl.opts.db.online.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author ylyang
 * @since 2021-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OptsOnlineOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务id
     */
    private Integer taskId;

    private Integer merchantId;

    /**
     * 手机号
     */
    private String merchantPhone;

    /**
     * 名称
     */
    private String merchantName;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 提交字段
     */
    private String subFiledJson;

    /**
     * 审核字段
     */
    private String reviewFiledJson;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 审核通过时间
     */
    private LocalDateTime finishTime;

    /**
     * 应结算金额
     */
    private BigDecimal predictIncome;

    /**
     * 实际结算金额
     */
    private BigDecimal realIncome;

    /**
     * 状态 0审核中, 1成功，2失败，3撤销
     */
    private Integer status;

    /**
     * 日期
     */
    private String workDate;

    /**
     * 失败原因
     */
    private String failMessage;

    /**
     * 备注
     */
    private String remark;


}
