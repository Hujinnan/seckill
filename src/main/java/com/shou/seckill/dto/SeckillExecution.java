package com.shou.seckill.dto;

import com.shou.seckill.entity.SuccessKilled;
import com.shou.seckill.enums.SeckillStateEnum;

/**
 * 封装用户秒杀后结果
 */
public class SeckillExecution {
    //用户Id
    private long seckillId;

    //秒杀状态码
    private int state;

    //秒杀状态码对应信息
    private String stateinfo;

    //秒杀成功对象
    private SuccessKilled successKilled;

    /**
     * 秒杀成功
     * @param seckillId
     * @param successKilled
     */
    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateinfo = seckillStateEnum.getStateinfo();
        this.successKilled = successKilled;
    }

    /**
     * 秒杀失败
     * @param seckillId
     */
    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateinfo = seckillStateEnum.getStateinfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    public void setStateinfo(String stateinfo) {
        this.stateinfo = stateinfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateinfo='" + stateinfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
