package top.shaozuo.geektime.java01.week09.service.impl;

import java.math.BigDecimal;

import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.shaozuo.geektime.java01.week09.dto.AccountDTO;
import top.shaozuo.geektime.java01.week09.service.AccountRmbService;
import top.shaozuo.geektime.java01.week09.service.AccountUsdService;
import top.shaozuo.geektime.java01.week09.service.ExchangeService;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    @Autowired
    private AccountRmbService accountRmbService;

    @Autowired
    private AccountUsdService accountUsdService;

    private static final BigDecimal EXCHANGE_RATE = new BigDecimal("7.0");

    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    @Override
    public void exchangeUsd2Rmb(String userId, BigDecimal amount) {
        //减少美元
        accountUsdService.minus(new AccountDTO(userId, amount));
        //增加人民币
        accountRmbService.add(new AccountDTO(userId, amount.multiply(EXCHANGE_RATE)));
        System.out.println("美元兑换人民币");
    }

    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    @Override
    public void exchangeRmb2Usd(String userId, BigDecimal amount) {
        //减少人民币
        accountRmbService.minus(new AccountDTO(userId, amount));
        //增加美元
        accountUsdService.add(
                new AccountDTO(userId, amount.divide(EXCHANGE_RATE, 2, BigDecimal.ROUND_CEILING)));

    }

    public void confirm(String userId, BigDecimal amount) {
        System.out.println("兑换确认");
    }

    public void cancel(String userId, BigDecimal amount) {
        System.out.println("兑换取消");
    }

}
