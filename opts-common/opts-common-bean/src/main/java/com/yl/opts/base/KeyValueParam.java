package com.yl.opts.base;

import lombok.Data;

/**
 * @author: ylyang
 * @date: 2021-05-22
 */
@Data
public class KeyValueParam {

    private String key;

    private String value;

    private Integer isAuditField;
}
