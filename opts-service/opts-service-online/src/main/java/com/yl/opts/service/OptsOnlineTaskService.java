package com.yl.opts.service;

import com.alibaba.nacos.common.model.RestResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yl.opts.online.form.OptsOnlineOrderReview;
import com.yl.opts.online.form.OptsOnlineTaskForm;
import com.yl.opts.online.form.OptsOnlineTaskOrderSubmitForm;
import com.yl.opts.online.vo.OptsOnlineTaskSubmitField;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author: ylyang
 * @date: 2021-05-22
 */
public interface OptsOnlineTaskService {

    /**
     * 新增任务
     * @param optsOnlineTaskForm
     */
    void insertOrUpdateTask(OptsOnlineTaskForm optsOnlineTaskForm);

    /**
     * 启用任务
     * @param id
     */
    void enableTask(Integer id);

    /**
     * 生成审核模版
     * @param id
     */
    void createReview(Integer id);

    /**
     * 订单列表
     * @param pageNo
     * @param pageSize
     * @param keyword
     * @param status
     * @param taskId
     * @return
     */
    Page listOrder(int pageNo, int pageSize, String keyword, Integer status, Integer taskId);


    /**
     * 审核订单
     * @param optsOnlineOrderReview
     */
    void reviewOrder(OptsOnlineOrderReview optsOnlineOrderReview);


    /**
     * 获取提交字段
     * @param taskId
     * @return
     */
    List<OptsOnlineTaskSubmitField> getSubmitFieldList(Integer taskId);

    /**
     * 用户提交订单接口
     * @param optsOnlineTaskOrderSubmitForm
     */
    void submitTaskOrder(OptsOnlineTaskOrderSubmitForm optsOnlineTaskOrderSubmitForm);
}
