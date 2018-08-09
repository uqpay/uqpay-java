package com.uqpay.sdk.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import com.uqpay.sdk.config.CashierConfig;
import com.uqpay.sdk.config.MerchantConfig;
import com.uqpay.sdk.config.PaygateConfig;


@ConfigurationProperties(prefix = "uqpay", ignoreUnknownFields = false)
public class UqpayProperties {

  /**
   * Merchant Info
   */
  @NestedConfigurationProperty
  private MerchantConfig merchant;

  /**
   * Payment Gateway Config
   */
  @NestedConfigurationProperty
  private PaygateConfig paygate;

  /**
   * UQPAY Cashier Config
   */
  @NestedConfigurationProperty
  private CashierConfig cashier;

  public MerchantConfig getMerchant() {
    return merchant;
  }

  public void setMerchant(MerchantConfig merchant) {
    this.merchant = merchant;
  }

  public PaygateConfig getPaygate() {
    return paygate;
  }

  public void setPaygate(PaygateConfig paygate) {
    this.paygate = paygate;
  }

  public CashierConfig getCashier() {
    return cashier;
  }

  public void setCashier(CashierConfig cashier) {
    this.cashier = cashier;
  }
}
