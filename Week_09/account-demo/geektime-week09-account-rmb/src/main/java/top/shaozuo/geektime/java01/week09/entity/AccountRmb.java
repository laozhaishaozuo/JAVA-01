package top.shaozuo.geektime.java01.week09.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRmb {

    private Integer id;

    private String userId;

    private BigDecimal balance;

    private BigDecimal freezeAmountAdd;

    private BigDecimal freezeAmountMinus;

    private Date createTime;

    private Date updateTime;

}
