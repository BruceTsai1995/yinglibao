package com.bjpowernode.api.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

//平台基本信息类
public class BaseInfo implements Serializable {
    /**收益率平均值*/
    private BigDecimal historyAvgRate;

    /**累计成交金额*/
    private BigDecimal sumBidMoney;

    /**注册人数*/
    private Integer registerUser;

    public BaseInfo() {
    }

    public BaseInfo(BigDecimal historyAvgRate, BigDecimal sumBidMoney, Integer registerUser) {
        this.historyAvgRate = historyAvgRate;
        this.sumBidMoney = sumBidMoney;
        this.registerUser = registerUser;
    }

    public BigDecimal getHistoryAvgRate() {
        return historyAvgRate;
    }

    public void setHistoryAvgRate(BigDecimal historyAvgRate) {
        this.historyAvgRate = historyAvgRate;
    }

    public BigDecimal getSumBidMoney() {
        return sumBidMoney;
    }

    public void setSumBidMoney(BigDecimal sumBidMoney) {
        this.sumBidMoney = sumBidMoney;
    }

    public Integer getRegisterUser() {
        return registerUser;
    }

    public void setRegisterUser(Integer registerUser) {
        this.registerUser = registerUser;
    }
}
