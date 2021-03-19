package top.shaozuo.geektime.java01.week09.service;

import org.dromara.hmily.annotation.Hmily;

import top.shaozuo.geektime.java01.week09.dto.AccountDTO;

/**
 * 对美元账户的操作
 * 
 * @author shaozuo
 *
 */
public interface AccountUsdService {

    /**
     * 增加指定金额
     *
     * @param accountDTO
     *            参数dto
     */
    @Hmily
    boolean add(AccountDTO accountDTO);

    /**
     * 扣款指定金额
     *
     * @param accountDTO
     *            参数dto
     */
    @Hmily
    boolean minus(AccountDTO accountDTO);

}
