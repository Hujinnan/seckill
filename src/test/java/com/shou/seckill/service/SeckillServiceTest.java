package com.shou.seckill.service;

import com.shou.seckill.entity.SecKill;
import com.shou.seckill.service.impl.SeckillServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xiaoz on 2017/7/23.
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() throws Exception {
        List<SecKill> list = seckillService.getSeckillList();

        logger.info("list={}",list);
    }

    @Test
    public void getById() throws Exception {
        SecKill secKill = seckillService.getById(1000);
        logger.info("seckill={}",secKill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {

    }

    @Test
    public void executeSeckill() throws Exception {

    }

}