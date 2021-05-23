package com.yl.opts;

import com.yl.OptsServiceCommunityApplication;
import com.yl.opts.db.community.entity.Dynamic;
import com.yl.opts.db.community.mapper.DynamicMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ylyang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OptsServiceCommunityApplication.class)
public class DynamicServiceTest {

    @Autowired
    DynamicMapper dynamicMapper;

    @Test
    public void test01(){
        Dynamic dynamic = new Dynamic();
        dynamic.setMerchantId(1);
        dynamic.setMerchantName("小明");
        dynamic.setTitle("这事一条动态");
        dynamic.setCategoryId(1);
        dynamic.setContent("asdfasdfasdfdsafasdf");
        dynamic.setType(1);
        dynamic.setUrl("");
        dynamic.setCreateTime(LocalDateTime.now());
        dynamic.setUpdateTime(LocalDateTime.now());
        dynamicMapper.insert(dynamic);

        List<String> list = new ArrayList<>();
        list.stream().forEach(System.out::println);

    }
}
