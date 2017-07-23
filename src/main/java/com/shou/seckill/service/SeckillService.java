package com.shou.seckill.service;

import com.shou.seckill.dto.ExportUrl;
import com.shou.seckill.dto.SeckillExecution;
import com.shou.seckill.entity.SecKill;
import com.shou.seckill.exception.RepeatException;
import com.shou.seckill.exception.SeckillCloseException;
import com.shou.seckill.exception.SeckillException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 秒杀相关业务类
 */

public interface SeckillService {
    /**
     * 所有秒杀记录
     * @return
     */
    List<SecKill> getSeckillList();

    /**
     * 单个秒杀记录
     * @param seckillId
     * @return
     */
    SecKill getById(long seckillId);

    /**
     *秒杀开启时 输出秒杀地址；否则输出秒杀时间和结束时间
     * @param seckillId
     */
    ExportUrl exportSeckillUrl(long seckillId);

    /**
     * 用户执行秒杀
     * @param seckillId
     * @param userphone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userphone, String md5)throws SeckillException,RepeatException,SeckillCloseException;
}
