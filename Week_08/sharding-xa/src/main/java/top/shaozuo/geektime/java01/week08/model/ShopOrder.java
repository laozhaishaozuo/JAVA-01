package top.shaozuo.geektime.java01.week08.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopOrder implements Serializable {
    private Long id;

    private String sn;

    private String orderStatus;

    private Long buyerId;

    private String buyerUsername;

    private String paymentMethodCode;

    private String paymentStatus;

    private String shippingStatus;

    private Date expire;

    private Date lockExpire;

    private BigDecimal fee;

    private BigDecimal freight;

    private BigDecimal tax;

    private BigDecimal totalAmount;

    private BigDecimal amountPayable;

    private BigDecimal amountPaid;

    private Date finishDate;

    private Date orderTime;

    private String orderMessage;

    private Date createDate;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public ShopOrder withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public ShopOrder withSn(String sn) {
        this.setSn(sn);
        return this;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public ShopOrder withOrderStatus(String orderStatus) {
        this.setOrderStatus(orderStatus);
        return this;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public ShopOrder withBuyerId(Long buyerId) {
        this.setBuyerId(buyerId);
        return this;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public ShopOrder withBuyerUsername(String buyerUsername) {
        this.setBuyerUsername(buyerUsername);
        return this;
    }

    public void setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
    }

    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public ShopOrder withPaymentMethodCode(String paymentMethodCode) {
        this.setPaymentMethodCode(paymentMethodCode);
        return this;
    }

    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public ShopOrder withPaymentStatus(String paymentStatus) {
        this.setPaymentStatus(paymentStatus);
        return this;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public ShopOrder withShippingStatus(String shippingStatus) {
        this.setShippingStatus(shippingStatus);
        return this;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Date getExpire() {
        return expire;
    }

    public ShopOrder withExpire(Date expire) {
        this.setExpire(expire);
        return this;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Date getLockExpire() {
        return lockExpire;
    }

    public ShopOrder withLockExpire(Date lockExpire) {
        this.setLockExpire(lockExpire);
        return this;
    }

    public void setLockExpire(Date lockExpire) {
        this.lockExpire = lockExpire;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public ShopOrder withFee(BigDecimal fee) {
        this.setFee(fee);
        return this;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public ShopOrder withFreight(BigDecimal freight) {
        this.setFreight(freight);
        return this;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public ShopOrder withTax(BigDecimal tax) {
        this.setTax(tax);
        return this;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public ShopOrder withTotalAmount(BigDecimal totalAmount) {
        this.setTotalAmount(totalAmount);
        return this;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAmountPayable() {
        return amountPayable;
    }

    public ShopOrder withAmountPayable(BigDecimal amountPayable) {
        this.setAmountPayable(amountPayable);
        return this;
    }

    public void setAmountPayable(BigDecimal amountPayable) {
        this.amountPayable = amountPayable;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public ShopOrder withAmountPaid(BigDecimal amountPaid) {
        this.setAmountPaid(amountPaid);
        return this;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public ShopOrder withFinishDate(Date finishDate) {
        this.setFinishDate(finishDate);
        return this;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public ShopOrder withOrderTime(Date orderTime) {
        this.setOrderTime(orderTime);
        return this;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public ShopOrder withOrderMessage(String orderMessage) {
        this.setOrderMessage(orderMessage);
        return this;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public ShopOrder withCreateDate(Date createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public ShopOrder withUpdateDate(Date updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public ShopOrder withRemarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public ShopOrder withDelFlag(String delFlag) {
        this.setDelFlag(delFlag);
        return this;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sn=").append(sn);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", buyerUsername=").append(buyerUsername);
        sb.append(", paymentMethodCode=").append(paymentMethodCode);
        sb.append(", paymentStatus=").append(paymentStatus);
        sb.append(", shippingStatus=").append(shippingStatus);
        sb.append(", expire=").append(expire);
        sb.append(", lockExpire=").append(lockExpire);
        sb.append(", fee=").append(fee);
        sb.append(", freight=").append(freight);
        sb.append(", tax=").append(tax);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", amountPayable=").append(amountPayable);
        sb.append(", amountPaid=").append(amountPaid);
        sb.append(", finishDate=").append(finishDate);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", orderMessage=").append(orderMessage);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", delFlag=").append(delFlag);
        sb.append("]");
        return sb.toString();
    }
}