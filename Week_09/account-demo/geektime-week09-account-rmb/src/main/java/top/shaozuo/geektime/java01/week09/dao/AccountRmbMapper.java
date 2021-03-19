
package top.shaozuo.geektime.java01.week09.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import top.shaozuo.geektime.java01.week09.dto.AccountDTO;

@Mapper
public interface AccountRmbMapper {

    /**
     * Update int.
     *
     * @param accountDTO
     *            the t_account_rmb dto
     * @return the int
     */
    @Update("update t_account_rmb set "
            + " freeze_amount_add = freeze_amount_add + #{amount} ,update_time = now()"
            + " where user_id =#{userId}")
    int add(AccountDTO accountDTO);

    /**
     * Confirm int.
     *
     * @param accountDTO
     *            the t_account_rmb dto
     * @return the int
     */
    @Update("update t_account_rmb set balance = balance + #{amount},"
            + " freeze_amount_add= freeze_amount_add - #{amount}" + " where user_id =#{userId} ")
    int confirmAdd(AccountDTO accountDTO);

    /**
     * Cancel int.
     *
     * @param accountDTO
     *            the t_account_rmb dto
     * @return the int
     */
    @Update("update t_account_rmb " + " freeze_amount_add= freeze_amount_add -  #{amount} "
            + " where user_id =#{userId}")
    int cancelAdd(AccountDTO accountDTO);

    /**
     * Update int.
     *
     * @param accountDTO
     *            the t_account_rmb dto
     * @return the int
     */
    @Update("update t_account_rmb set balance = balance - #{amount},"
            + " freeze_amount_minus= freeze_amount_minus + #{amount} ,update_time = now()"
            + " where user_id =#{userId}  and  balance > 0  ")
    int minus(AccountDTO accountDTO);

    /**
     * Confirm int.
     *
     * @param accountDTO
     *            the t_account_rmb dto
     * @return the int
     */
    @Update("update t_account_rmb set freeze_amount_minus= freeze_amount_minus - #{amount}"
            + " where user_id =#{userId}  and freeze_amount_minus >0 ")
    int confirmMinus(AccountDTO accountDTO);

    /**
     * Cancel int.
     *
     * @param accountDTO
     *            the t_account_rmb dto
     * @return the int
     */
    @Update("update t_account_rmb set balance = balance + #{amount},"
            + " freeze_amount_minus= freeze_amount_minus -  #{amount} "
            + " where user_id =#{userId}  and freeze_amount_minus >0")
    int cancelMinus(AccountDTO accountDTO);

}
