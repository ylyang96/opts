package com.yl.opts.online.form;

import lombok.Data;

/**
 * @author: ylyang
 * @date: 2021-05-22
 */
@Data
public class OptsOnlineTaskParam {

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

    /**
     * 正则校验
     */
    private String regular;

    /**
     * 扩展字段
     */
    private String extend;

    /**
     * 1可用，0不可用
     */
    private Integer status;

    private Integer type;

}
