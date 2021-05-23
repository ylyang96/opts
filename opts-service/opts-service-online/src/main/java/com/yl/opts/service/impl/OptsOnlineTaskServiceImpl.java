package com.yl.opts.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yl.opts.base.EnumCommon;
import com.yl.opts.common.core.util.SnowflakeIdWorker;
import com.yl.opts.db.online.entity.OptsOnlineOrder;
import com.yl.opts.db.online.entity.OptsOnlineOrderParamConfig;
import com.yl.opts.db.online.entity.OptsOnlineTask;
import com.yl.opts.db.online.mapper.OptsOnlineOrderMapper;
import com.yl.opts.db.online.mapper.OptsOnlineOrderParamConfigMapper;
import com.yl.opts.db.online.mapper.OptsOnlineTaskMapper;
import com.yl.opts.online.enums.EnumOptsOnlineTask;
import com.yl.opts.online.form.OptsOnlineOrderReview;
import com.yl.opts.online.form.OptsOnlineTaskForm;
import com.yl.opts.online.form.OptsOnlineTaskOrderSubmitForm;
import com.yl.opts.online.form.OptsOnlineTaskParam;
import com.yl.opts.online.vo.OptsOnlineTaskSubmitField;
import com.yl.opts.service.OptsOnlineTaskService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ylyang
 * @date: 2021-05-22
 */
@Service
public class OptsOnlineTaskServiceImpl implements OptsOnlineTaskService {

    @Autowired
    private OptsOnlineTaskMapper optsOnlineTaskMapper;

    @Autowired
    private OptsOnlineOrderParamConfigMapper optsOnlineOrderParamConfigMapper;

    @Autowired
    private OptsOnlineOrderMapper optsOnlineOrderMapper;


    private SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1,11);


    @Override
    public void insertOrUpdateTask(OptsOnlineTaskForm optsOnlineTaskForm) {
        OptsOnlineTask optsOnlineTask = new OptsOnlineTask();
        BeanUtils.copyProperties(optsOnlineTaskForm, optsOnlineTask);
        Integer taskId = 0;
        if (optsOnlineTaskForm.getId()==null) {
            //新增
            optsOnlineTask.setCreateTime(LocalDateTime.now());
            optsOnlineTask.setUpdateTime(LocalDateTime.now());
            //默认未使用
            optsOnlineTask.setStatus(EnumCommon.EnumEnableStop.STOP.getKey());
            int insert = optsOnlineTaskMapper.insert(optsOnlineTask);
            taskId = insert;
        }else{
            optsOnlineTask.setUpdateTime(LocalDateTime.now());
            optsOnlineTask.setId(optsOnlineTaskForm.getId());
            optsOnlineTaskMapper.insert(optsOnlineTask);
            taskId = optsOnlineTaskForm.getId();
        }
        Integer finalTaskId = taskId;
        optsOnlineTaskForm.filedList.forEach(one -> dealParamConfig(one, finalTaskId));
    }

    private void dealParamConfig(OptsOnlineTaskParam optsOnlineTaskParam, Integer taskId){
        OptsOnlineOrderParamConfig bean = getBean(optsOnlineTaskParam);
        if (optsOnlineTaskParam.getId()==null) {
            bean.setBindId(taskId);
            bean.setCreateTime(LocalDateTime.now());
            optsOnlineOrderParamConfigMapper.insert(bean);
        }else{
            bean.setId(optsOnlineTaskParam.getId());
            optsOnlineOrderParamConfigMapper.updateById(bean);
        }
    }

    private OptsOnlineOrderParamConfig getBean(OptsOnlineTaskParam optsOnlineTaskParam){
        OptsOnlineOrderParamConfig optsOnlineOrderParamConfig = new OptsOnlineOrderParamConfig();
        optsOnlineOrderParamConfig.setFieldKey(optsOnlineTaskParam.getFieldKey());
        optsOnlineOrderParamConfig.setFiledValue(optsOnlineTaskParam.getFiledValue());
        optsOnlineOrderParamConfig.setDisplayValue(optsOnlineTaskParam.getDisplayValue());
        optsOnlineOrderParamConfig.setTipsValue(optsOnlineTaskParam.getTipsValue());
        optsOnlineOrderParamConfig.setIsMust(optsOnlineTaskParam.getIsMust());
        optsOnlineOrderParamConfig.setIsAuditField(optsOnlineTaskParam.getIsAuditField());
        //optsOnlineOrderParamConfig.setType(one.get);
        optsOnlineOrderParamConfig.setState(optsOnlineTaskParam.getStatus());
        optsOnlineOrderParamConfig.setRegular(optsOnlineTaskParam.getRegular());
        optsOnlineOrderParamConfig.setUpdateTime(LocalDateTime.now());
        optsOnlineOrderParamConfig.setExtend(optsOnlineTaskParam.getExtend());
        optsOnlineOrderParamConfig.setType(optsOnlineTaskParam.getType());
        return optsOnlineOrderParamConfig;
    }

    /**
     * 启用任务首先要判断是否有使用的提交字段才行
     * @param id
     */
    @Override
    public void enableTask(Integer id) {
        QueryWrapper<OptsOnlineOrderParamConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OptsOnlineOrderParamConfig::getBindId, id).eq(OptsOnlineOrderParamConfig::getState, EnumCommon.EnumEnableStop.ENABLE.getKey());
        List<OptsOnlineOrderParamConfig> optsOnlineOrderParamConfigs = optsOnlineOrderParamConfigMapper.selectList(queryWrapper);
        if (optsOnlineOrderParamConfigs == null || optsOnlineOrderParamConfigs.size() == 0){
            throw new RuntimeException("请先创建提交字段,或者启用提交字段");
        }
        OptsOnlineTask optsOnlineTask = new OptsOnlineTask();
        optsOnlineTask.setId(id);
        optsOnlineTask.setStatus(EnumCommon.EnumEnableStop.ENABLE.getKey());
        optsOnlineTask.setUpdateTime(LocalDateTime.now());
        optsOnlineTaskMapper.updateById(optsOnlineTask);
    }

    @Override
    public void createReview(Integer id) {
        OptsOnlineTask optsOnlineTask = optsOnlineTaskMapper.selectById(id);
        if (optsOnlineTask==null || EnumCommon.EnumEnableStop.STOP.getKey().equals(optsOnlineTask.getStatus())){
            throw new RuntimeException("id异常或者该任务还未启用");
        }
        QueryWrapper<OptsOnlineOrderParamConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OptsOnlineOrderParamConfig::getBindId, id).eq(OptsOnlineOrderParamConfig::getState, EnumCommon.EnumEnableStop.ENABLE.getKey())
                .eq(OptsOnlineOrderParamConfig::getIsAuditField, EnumOptsOnlineTask.Base.IS_AUDIT_FIELD);
        List<OptsOnlineOrderParamConfig> optsOnlineOrderParamConfigs = optsOnlineOrderParamConfigMapper.selectList(queryWrapper);
        String title = optsOnlineTask.getTaskName();
        //sheet页的标题
        List<String> collect = optsOnlineOrderParamConfigs.stream().map(OptsOnlineOrderParamConfig::getDisplayValue).collect(Collectors.toList());
    }

    @Override
    public Page listOrder(int pageNo, int pageSize, String keyword, Integer status, Integer taskId) {
        QueryWrapper<OptsOnlineOrder> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(keyword)){
            keyword = ""+keyword+"";
            String finalKeyword = keyword;
            queryWrapper.and(wrapper->wrapper.lambda().like(OptsOnlineOrder::getMerchantName, finalKeyword).or().like(OptsOnlineOrder::getMerchantPhone, finalKeyword));
        }
        if (taskId!=null){
            queryWrapper.lambda().eq(OptsOnlineOrder::getTaskId, taskId);
        }
        if (status!=null){
            queryWrapper.lambda().eq(OptsOnlineOrder::getStatus, status);
        }
        //Integer integer = optsOnlineOrderMapper.selectCount(queryWrapper);
        Page<OptsOnlineOrder> page = new Page<>();
        optsOnlineOrderMapper.selectPage(page, queryWrapper);
        return page;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void reviewOrder(OptsOnlineOrderReview optsOnlineOrderReview) {
        OptsOnlineTask optsOnlineTask = optsOnlineTaskMapper.selectById(optsOnlineOrderReview.getTaskId());
        if (optsOnlineTask==null){
            throw new RuntimeException("任务id异常");
        }

        JSONObject jsonObject = new JSONObject();
        optsOnlineOrderReview.getList().forEach(one->{
            jsonObject.put(one.getFiledKey(), one.getValue());
        });
        QueryWrapper<OptsOnlineOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OptsOnlineOrder::getReviewFiledJson, jsonObject.toString()).eq(OptsOnlineOrder::getTaskId, optsOnlineOrderReview.getTaskId());
        OptsOnlineOrder optsOnlineOrder = optsOnlineOrderMapper.selectOne(queryWrapper);
        if (optsOnlineOrder==null){
            return;
        }
        OptsOnlineOrder updateBean = new OptsOnlineOrder();
        updateBean.setId(optsOnlineOrder.getId());
        updateBean.setFinishTime(LocalDateTime.now());
        updateBean.setStatus(EnumCommon.EnumOrder.SUCCESS.getStatus());
        //判断任务是否是限量的
        if (EnumOptsOnlineTask.Base.TASK_NUM_TYPE.equals(optsOnlineTask.getTaskNumType())){
            Integer integer = optsOnlineTaskMapper.updateLimitNum(optsOnlineTask.getId(), 1);
            if (integer==null || integer==0){
                throw new RuntimeException("剩余的任务数量不够不能审核通过");
            }
        }
        //todo task进行其他操作
    }

    @Override
    public List<OptsOnlineTaskSubmitField> getSubmitFieldList(Integer taskId) {
        QueryWrapper<OptsOnlineOrderParamConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OptsOnlineOrderParamConfig::getBindId, taskId).eq(OptsOnlineOrderParamConfig::getState, EnumCommon.EnumEnableStop.ENABLE.getKey());
        List<OptsOnlineOrderParamConfig> optsOnlineOrderParamConfigs = optsOnlineOrderParamConfigMapper.selectList(queryWrapper);
        if (optsOnlineOrderParamConfigs == null || optsOnlineOrderParamConfigs.size() == 0){
            throw new RuntimeException("任务异常 请稍后重试");
        }
        List<OptsOnlineTaskSubmitField> collect = optsOnlineOrderParamConfigs.stream().map(one -> {
            OptsOnlineTaskSubmitField optsOnlineTaskSubmitField = new OptsOnlineTaskSubmitField();
            BeanUtils.copyProperties(one, optsOnlineTaskSubmitField);
            return optsOnlineTaskSubmitField;
        }).collect(Collectors.toList());
        return collect;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void submitTaskOrder(OptsOnlineTaskOrderSubmitForm optsOnlineTaskOrderSubmitForm) {
        OptsOnlineTask optsOnlineTask = optsOnlineTaskMapper.selectById(optsOnlineTaskOrderSubmitForm.getTaskId());
        if (optsOnlineTask==null){
            throw new RuntimeException("任务id参数错误");
        }
        OptsOnlineOrder optsOnlineOrder = new OptsOnlineOrder();

        optsOnlineOrder.setTaskId(optsOnlineTaskOrderSubmitForm.getTaskId());
        //暂且先写死
        optsOnlineOrder.setMerchantId(12);
        optsOnlineOrder.setMerchantPhone("12345678901");
        optsOnlineOrder.setMerchantName("张三");

        optsOnlineOrder.setOrderNo(getOrderNo());
        JSONObject subJson = new JSONObject();
        JSONObject reviewJson = new JSONObject();
        //这里要根据审核字段去查有没有人提过对应的订单
        QueryWrapper<OptsOnlineOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OptsOnlineOrder::getReviewFiledJson, reviewJson.toString());
        OptsOnlineOrder order = optsOnlineOrderMapper.selectOne(queryWrapper);
        if (order!=null){
            //判断是否是同一个提交人，如果不是就直接报错return
            //如果是一个提交人 判断订单的状态 如果是待审核 就修改不然就报错提示重复订单
        }
        optsOnlineTaskOrderSubmitForm.getList().forEach(one->{
            if (EnumOptsOnlineTask.Base.NOT_IS_AUDIT_FIELD.equals(one.getIsAuditField())){
                reviewJson.put(one.getKey(), one.getValue());
            }
            subJson.put(one.getKey(), one.getValue());
        });

        optsOnlineOrder.setSubFiledJson(subJson.toString());
        optsOnlineOrder.setReviewFiledJson(reviewJson.toString());
        optsOnlineOrder.setCreateTime(LocalDateTime.now());
        optsOnlineOrder.setUpdateTime(LocalDateTime.now());
//        optsOnlineOrder.setFinishTime();
        optsOnlineOrder.setPredictIncome(optsOnlineTask.getBounty());
//        optsOnlineOrder.setRealIncome();
        optsOnlineOrder.setStatus(EnumCommon.EnumOrder.SUBMIT.getStatus());
        optsOnlineOrder.setWorkDate(LocalDate.now().toString());
//        optsOnlineOrder.setFailMessage();
//        optsOnlineOrder.setRemark();
        optsOnlineOrderMapper.insert(optsOnlineOrder);
    }

    private String getOrderNo(){
        return "online"+snowflakeIdWorker.nextId();
    }
}
