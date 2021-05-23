package com.yl.opts.online.form;

import lombok.Data;

import java.util.List;

/**
 * @author: ylyang
 * @date: 2021-05-22
 */
@Data
public class OptsOnlineOrderReview {


    private List<OptsOnlineOrderReviewDetail> list;



    private Integer taskId;
}
