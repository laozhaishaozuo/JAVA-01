package top.shaozuo.geektime.java01.week09.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import top.shaozuo.geektime.java01.week09.service.ExchangeService;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @PostMapping(value = "/usd2rmb")
    @ApiOperation(value = "美元兑换人民币")
    public String usd2rmb(@RequestParam(value = "userId") String userId,
            @RequestParam(value = "amount") BigDecimal amount) {
        final long start = System.currentTimeMillis();
        exchangeService.exchangeUsd2Rmb(userId, amount);
        System.out.println("消耗时间为:" + (System.currentTimeMillis() - start));
        return "";
    }

    @PostMapping(value = "/rmb2usd")
    @ApiOperation(value = "人民币兑换美元")
    public String rmb2usd(@RequestParam(value = "userId") String userId,
            @RequestParam(value = "amount") BigDecimal amount) {
        final long start = System.currentTimeMillis();
        exchangeService.exchangeRmb2Usd(userId, amount);
        System.out.println("消耗时间为:" + (System.currentTimeMillis() - start));
        return "";
    }
}
