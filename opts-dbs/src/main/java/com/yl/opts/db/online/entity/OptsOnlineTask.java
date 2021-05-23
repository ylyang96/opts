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
 * 订单任务表
 * </p>
 *
 * @author ylyang
 * @since 2021-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OptsOnlineTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 0待使用, 1使用中,2停用
     */
    private Integer status;

    /**
     * 赏金
     */
    private BigDecimal bounty;

    /**
     * 0无限制，1有限制
     */
    private Integer taskNumType;

    /**
     * 任务次数
     */
    private Integer taskNum;

    /**
     * 卖点
     */
    private String sellingPoint;

    /**
     * 标签
     */
    private String label;

    /**
     * 截止日期
     */
    private LocalDateTime deadline;

    /**
     * 每日限制提交次数,-1不限制
     */
    private Integer limitType;

    /**
     * 是否推荐 0不推荐 1推荐
     */
    private Integer isSuggest;

    /**
     * 步骤流程
     */
    private String stepDescription;

    /**
     * 流程描述
     */
    private String processDescription;

    /**
     * 流程图片
     */
    private String processUrl;

    /**
     * 提示文案
     */
    private String tipContent;

    /**
     * 备注
     */
    private String remark;

    /**
     * 审核时间
     */
    private String reviewTimeValue;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer bmUserId;

    private String bmUserName;


}
