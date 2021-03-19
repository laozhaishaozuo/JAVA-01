package top.shaozuo.geektime.java01.week09.service;

import org.dromara.hmily.annotation.HmilyTCC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.shaozuo.geektime.java01.week09.dao.AccountUsdMapper;
import top.shaozuo.geektime.java01.week09.dto.AccountDTO;

@Service("accountUsdService")
public class AccountUsdServiceImpl implements AccountUsdService {
    /**
     * logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(AccountUsdServiceImpl.class);

    @Autowired
    private AccountUsdMapper accountUsdMapper;

    @HmilyTCC(confirmMethod = "confirmAdd", cancelMethod = "cancelAdd")
    @Override
    public boolean add(AccountDTO accountDTO) {
        logger.info("{} 增加美元 {}", accountDTO.getUserId(), accountDTO.getAmount());
        return accountUsdMapper.add(accountDTO) > 0;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmMinus", cancelMethod = "cancelMinus")
    public boolean minus(AccountDTO accountDTO) {
        logger.info("{} 减少美元 {}", accountDTO.getUserId(), accountDTO.getAmount());
        return accountUsdMapper.minus(accountDTO) > 0;
    }

    /**
     * Confirm boolean.
     *
     * @param accountDTO
     *            the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmAdd(AccountDTO accountDTO) {
        logger.info("确认 {} 增加美元 {}", accountDTO.getUserId(), accountDTO.getAmount());
        accountUsdMapper.confirmAdd(accountDTO);
        return Boolean.TRUE;
    }

    /**
     * Cancel boolean.
     *
     * @param accountDTO
     *            the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelAdd(AccountDTO accountDTO) {
        logger.info("取消 {} 增加美元 {}", accountDTO.getUserId(), accountDTO.getAmount());
        accountUsdMapper.cancelAdd(accountDTO);
        return Boolean.TRUE;
    }

    /**
     * Confirm boolean.
     *
     * @param accountDTO
     *            the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmMinus(AccountDTO accountDTO) {
        logger.info("确认 {} 减少美元 {}", accountDTO.getUserId(), accountDTO.getAmount());
        accountUsdMapper.confirmMinus(accountDTO);
        return Boolean.TRUE;
    }

    /**
     * Cancel boolean.
     *
     * @param accountDTO
     *            the account dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelMinus(AccountDTO accountDTO) {
        logger.info("取消 {} 减少美元 {}", accountDTO.getUserId(), accountDTO.getAmount());
        accountUsdMapper.cancelMinus(accountDTO);
        return Boolean.TRUE;
    }
}
