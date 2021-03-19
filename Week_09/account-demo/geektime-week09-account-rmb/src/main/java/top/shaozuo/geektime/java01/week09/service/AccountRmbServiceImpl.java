package top.shaozuo.geektime.java01.week09.service;

import org.dromara.hmily.annotation.HmilyTCC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.shaozuo.geektime.java01.week09.dao.AccountRmbMapper;
import top.shaozuo.geektime.java01.week09.dto.AccountDTO;

@Service("accountRmbService")
public class AccountRmbServiceImpl implements AccountRmbService {
    /**
     * logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(AccountRmbServiceImpl.class);

    @Autowired
    private AccountRmbMapper accountRmbMapper;

    @HmilyTCC(confirmMethod = "confirmAdd", cancelMethod = "cancelAdd")
    @Override
    public boolean add(AccountDTO accountDTO) {
        logger.info("{} 增加人民币 {}", accountDTO.getUserId(), accountDTO.getAmount());
        return accountRmbMapper.add(accountDTO) > 0;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmMinus", cancelMethod = "cancelMinus")
    public boolean minus(AccountDTO accountDTO) {
        logger.info("{} 减少人民币 {}", accountDTO.getUserId(), accountDTO.getAmount());
        return accountRmbMapper.minus(accountDTO) > 0;
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
        logger.info("确认 {} 增加人民币 {}", accountDTO.getUserId(), accountDTO.getAmount());
        accountRmbMapper.confirmAdd(accountDTO);
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
        logger.info("取消 {} 增加人民币 {}", accountDTO.getUserId(), accountDTO.getAmount());
        accountRmbMapper.cancelAdd(accountDTO);
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
        logger.info("确认 {} 减少人民币 {}", accountDTO.getUserId(), accountDTO.getAmount());
        accountRmbMapper.confirmMinus(accountDTO);
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
        logger.info("取消 {} 减少人民币 {}", accountDTO.getUserId(), accountDTO.getAmount());
        accountRmbMapper.cancelMinus(accountDTO);
        return Boolean.TRUE;
    }
}
