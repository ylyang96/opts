package com.yl.opts.online.vo;

import lombok.Data;

/**
 * @author: ylyang
 * @date: 2021-05-22
 */
@Data
public class OptsOnlineTaskSubmitField {

    private String filedKey;

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
     * 0 文字，1图片
     */
    private Integer type;


    /**
     * 正则校验
     */
    private String regular;
}
