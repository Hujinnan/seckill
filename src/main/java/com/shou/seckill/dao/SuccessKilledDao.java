package com.shou.seckill.dao;

import com.shou.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by xiaoz on 2017/6/22.
 */
public interface SuccessKilledDao {
    /**
     * 插入秒杀用户
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccesKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled querybyIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
