package top.shaozuo.geektime.java01.week09.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 金额
     */
    private BigDecimal amount;

    public AccountDTO() {
    }

    public AccountDTO(String userId, BigDecimal amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
