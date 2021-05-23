package com.yl.opts.base;

/**
 * @author: ylyang
 * @date: 2021-05-22
 */
public class EnumCommon {

    public enum EnumEnableStop{

        /**
         * 启用
         */
        ENABLE(1),
        STOP(0);
        ;
        private Integer key;

        EnumEnableStop(Integer key) {
            this.key = key;
        }

        public Integer getKey() {
            return key;
        }
    }


    public enum EnumOrder{
        /**
         * 提交
         */
        SUBMIT(0),
        SUCCESS(1),
        FAIL(2),
        REVOKE(3),
        ;

        private Integer status;

        EnumOrder(Integer status) {
            this.status = status;
        }

        public Integer getStatus() {
            return status;
        }
    }
}
