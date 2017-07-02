package com.shou.seckill.entity;

import java.util.Date;

/**
 * Created by xiaoz on 2017/6/21.
 */

public class SuccessKilled {
    /**
     * 秒杀用户表
     */

    //商品id
    private long seckillId;
    //用户手机号码
    private long userPhone;
    //秒杀状态
    private short state;
    //创建时间
    private Date createTime;

    //一个用户对应多个商品
    private SecKill secKill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SecKill getSecKill() {
        return secKill;
    }

    public void setSecKill(SecKill secKill) {
        this.secKill = secKill;
    }

    public SuccessKilled() {
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", secKill=" + secKill +
                '}';
    }
}
