package top.shaozuo.geektime.java01.week09.service;

import java.math.BigDecimal;

public interface ExchangeService {
    /**
     * 用美元兑换人民币
     * 
     * @param userId
     * @param amount
     */
    void exchangeUsd2Rmb(String userId, BigDecimal amount);

    /**
     * 用人民币兑换美元
     * 
     * @param userId
     * @param amount
     */
    void exchangeRmb2Usd(String userId, BigDecimal amount);

}
