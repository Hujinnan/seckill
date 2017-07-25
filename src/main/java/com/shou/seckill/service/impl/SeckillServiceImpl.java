package com.shou.seckill.service.impl;

import com.shou.seckill.dao.SecKillDao;
import com.shou.seckill.dao.SuccessKilledDao;
import com.shou.seckill.dto.ExportUrl;
import com.shou.seckill.dto.SeckillExecution;
import com.shou.seckill.entity.SecKill;
import com.shou.seckill.entity.SuccessKilled;
import com.shou.seckill.enums.SeckillStateEnum;
import com.shou.seckill.exception.RepeatException;
import com.shou.seckill.exception.SeckillCloseException;
import com.shou.seckill.exception.SeckillException;
import com.shou.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by xiaoz on 2017/7/23.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillDao secKillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    //md5加盐，用于混淆MD5
    private final String salt = "asdafs124%^787*^^&&**>?KL";

    public List<SecKill> getSeckillList() {
        return secKillDao.queryAll(0, 4);
    }

    public SecKill getById(long seckillId) {
        return secKillDao.queryById(seckillId);
    }

    public ExportUrl exportSeckillUrl(long seckillId) {
        SecKill seckill = secKillDao.queryById(seckillId);
        if (seckill == null) {
            return new ExportUrl(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new ExportUrl(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(seckillId);
        return new ExportUrl(true,md5,seckillId);
    }

    private String getMD5(long seckillId){
        String base = seckillId+"/"+salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userphone, String md5) throws SeckillException, RepeatException, SeckillCloseException {
        if(md5 == null || !md5.equals(getMD5(seckillId))){
            throw new SeckillException("秒杀信息被重写 ");
        }
        //执行秒杀业务： 减库存+记录购买行为
        Date nowTime = new Date();
        try {
            //减库存
            int updateCount = secKillDao.reduceNumber(seckillId,nowTime);
            if(updateCount<=0){
                //没有更新到记录，秒杀结束
                throw new SeckillCloseException("秒杀关闭");
            }else{
                //记录购买行为
                int insertCount = successKilledDao.insertSuccesKilled(seckillId,userphone);
                if(insertCount<=0){
                    //重复秒杀
                    throw new RepeatException("重复秒杀");
                }else{
                    //秒杀成功
                    SuccessKilled successKilled=successKilledDao.querybyIdWithSeckill(seckillId,userphone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,successKilled);
                }
            }
        }catch (SeckillCloseException e1){
            //logger.info("秒杀关闭",e1);
            throw (e1);
        }catch (RepeatException e2){
            //logger.info("重复秒杀",e2);
            throw (e2);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new SeckillException("秒杀异常"+e.getMessage());
        }
    }
}
