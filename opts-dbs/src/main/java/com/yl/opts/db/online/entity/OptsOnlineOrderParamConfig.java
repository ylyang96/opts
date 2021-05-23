package com.yl.opts.db.online.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 订单参数配置表
 * </p>
 *
 * @author ylyang
 * @since 2021-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OptsOnlineOrderParamConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 字段key
     */
    private String fieldKey;

    /**
     * 字段value
     */
    private String filedValue;

    /**
     * 显示名称
     */
    private String displayValue;

    /**
     * 提示值
     */
    private String tipsValue;

    /**
     * 0非必输，1必输
     */
    private Integer isMust;

    /**
     * 0是审核字段， 1不是审核字段
     */
    private Integer isAuditField;

    private Integer type;

    /**
     * 1可用，0不可用
     */
    private Integer state;

    /**
     * 绑定的任务id
     */
    private Integer bindId;

    /**
     * 正则校验
     */
    private String regular;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 扩展字段
     */
    private String extend;


}
