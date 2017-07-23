package seckill;

import com.shou.seckill.dao.SecKillDao;
import com.shou.seckill.entity.SecKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * Created by xiaoz on 2017/6/24.
 * 配置spring 和junit整合， junit启动是加载springIoc容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SecKillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SecKillDao secKillDao;

    @Test
    public void reduceNumber() throws Exception {
        long id=1000L;
        Date date=new Date();
        int num=secKillDao.reduceNumber(id,date);
        System.out.println(num);
    }

    @Test
    public void queryById() throws Exception {
        long id=1000L;
        SecKill secKill=secKillDao.queryById(id);
        System.out.println(secKill.getNumber());
        System.out.println(secKill);
    }

    @Test
    public void queryAll() throws Exception {
        List<SecKill> secKills=secKillDao.queryAll(0,100);
        for (SecKill seckill:secKills) {
            System.out.println(seckill);
        }
    }

    public static void main(String[] args) {

        System.out.println("计算机软件".intern());
        String str1 = new StringBuilder("计算机").append("软件").toString();
        String str2 = new StringBuilder("ja").append("va").toString();

        System.out.println(str1.intern() == str1);
        System.out.println(str2.intern() == str2);

    }

}