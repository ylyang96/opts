package com.yl.opts.online.enums;

/**
 * @author: ylyang
 * @date: 2021-05-22
 */
public class EnumOptsOnlineTask {

    public interface Base{
        //是否必须
        Integer IS_MUST = 1;
        Integer NOT_IS_MUST = 0;
        //建议
        Integer IS_SUGGEST = 1;
        Integer NOT_IS_SUGGEST = 0;

        //是否审核字段 isAuditField
        Integer IS_AUDIT_FIELD = 1;
        Integer NOT_IS_AUDIT_FIELD = 1;

        //是否限制taskNumType 0无限制， 1有限制
        Integer TASK_NUM_TYPE = 1;
        Integer NOT_TASK_NUM_TYPE = 0;


    }
}
