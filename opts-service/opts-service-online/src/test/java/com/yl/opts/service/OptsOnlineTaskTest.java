package com.yl.opts.service;

import com.yl.opts.OnlineOptsServiceApplication;
import com.yl.opts.base.KeyValueParam;
import com.yl.opts.online.form.OptsOnlineTaskForm;
import com.yl.opts.online.form.OptsOnlineTaskOrderSubmitForm;
import com.yl.opts.online.form.OptsOnlineTaskParam;
import com.yl.opts.online.vo.OptsOnlineTaskSubmitField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ylyang
 * @date: 2021-05-22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineOptsServiceApplication.class)
public class OptsOnlineTaskTest {

    @Autowired
    private OptsOnlineTaskService optsOnlineTaskService;

    @Test
    public void insertTaskTest(){
        OptsOnlineTaskForm optsOnlineTaskForm = new OptsOnlineTaskForm();
        optsOnlineTaskForm.setTaskName("滴滴拉新");
        //optsOnlineTaskForm.setStatus();
        optsOnlineTaskForm.setBounty(new BigDecimal("12"));
        optsOnlineTaskForm.setTaskNumType(0);
        optsOnlineTaskForm.setTaskNum(1000);
        optsOnlineTaskForm.setSellingPoint("简单，结算快");
        optsOnlineTaskForm.setLabel("这事一个标签");
        optsOnlineTaskForm.setDeadline(LocalDateTime.now().plusYears(1));
        optsOnlineTaskForm.setLimitType(10);
        optsOnlineTaskForm.setIsSuggest(0);
        optsOnlineTaskForm.setStepDescription("下载滴滴app,通过特定邀请码注册即可");
        optsOnlineTaskForm.setProcessDescription("");
        optsOnlineTaskForm.setProcessUrl("processUrl.jpg");
        optsOnlineTaskForm.setTipContent(null);
        optsOnlineTaskForm.setRemark(null);
        optsOnlineTaskForm.setReviewTimeValue("提交三日内审核");
        OptsOnlineTaskParam optsOnlineTaskParam = new OptsOnlineTaskParam();
        //optsOnlineTaskParam.setId();
        optsOnlineTaskParam.setFieldKey("phone");
        optsOnlineTaskParam.setFiledValue("手机号");
        optsOnlineTaskParam.setDisplayValue("手机号");
        optsOnlineTaskParam.setTipsValue("请输入手机号");
        optsOnlineTaskParam.setIsMust(1);
        optsOnlineTaskParam.setIsAuditField(1);
        optsOnlineTaskParam.setRegular("^1[0-9]{10}$");
        optsOnlineTaskParam.setExtend(null);
        optsOnlineTaskParam.setStatus(1);
        optsOnlineTaskParam.setType(0);
        List<OptsOnlineTaskParam> list = new ArrayList<>();

        OptsOnlineTaskParam optsOnlineTaskParam2 = new OptsOnlineTaskParam();
        //optsOnlineTaskParam.setId();
        optsOnlineTaskParam2.setFieldKey("name");
        optsOnlineTaskParam2.setFiledValue("姓名");
        optsOnlineTaskParam2.setDisplayValue("姓名");
        optsOnlineTaskParam2.setTipsValue("请输入姓名");
        optsOnlineTaskParam2.setIsMust(0);
        optsOnlineTaskParam2.setIsAuditField(1);
        optsOnlineTaskParam2.setRegular(null);
        optsOnlineTaskParam2.setExtend(null);
        optsOnlineTaskParam2.setStatus(1);
        optsOnlineTaskParam2.setType(0);

        list.add(optsOnlineTaskParam);
        list.add(optsOnlineTaskParam2);
        optsOnlineTaskForm.setFiledList(list);
        optsOnlineTaskService.insertOrUpdateTask(optsOnlineTaskForm);
    }

    @Test
    public void enableTaskTest(){
        optsOnlineTaskService.enableTask(1);
    }

    @Test
    public void testSubmitFieldList001(){
        List<OptsOnlineTaskSubmitField> submitFieldList = optsOnlineTaskService.getSubmitFieldList(1);
        System.out.println(submitFieldList);
    }

    @Test
    public void addOrder(){
        OptsOnlineTaskOrderSubmitForm optsOnlineTaskOrderSubmitForm = new OptsOnlineTaskOrderSubmitForm();
        optsOnlineTaskOrderSubmitForm.setTaskId(1);
        KeyValueParam keyValueParam = new KeyValueParam();
        keyValueParam.setKey("phone");
        keyValueParam.setValue("131199614561");
        keyValueParam.setIsAuditField(1);

        KeyValueParam keyValueParam2 = new KeyValueParam();
        keyValueParam2.setKey("name");
        keyValueParam2.setValue("小王");
        keyValueParam2.setIsAuditField(0);

        List<KeyValueParam> list = new ArrayList<>();
        list.add(keyValueParam);
        list.add(keyValueParam2);
        optsOnlineTaskOrderSubmitForm.setList(list);
        optsOnlineTaskService.submitTaskOrder(optsOnlineTaskOrderSubmitForm);
    }



}
