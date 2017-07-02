package seckill;

import com.shou.seckill.dao.SuccessKilledDao;
import com.shou.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by xiaoz on 2017/6/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccesKilled() throws Exception {
        //通过联合主键 保证不能重复插入  PRIMARY KEY (seckill_id,user_phone)
        int insertCount=successKilledDao.insertSuccesKilled(1001,15900519999L);
        System.out.println(insertCount);
    }

    @Test
    public void querybyIdWithSeckill() throws Exception {
        long secKillId=1001;
        long userPhone=15900519999L;
        SuccessKilled successKilled=successKilledDao.querybyIdWithSeckill(secKillId,userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSecKill());
    }

}