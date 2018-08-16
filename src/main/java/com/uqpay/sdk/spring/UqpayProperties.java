package com.uqpay.sdk.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import com.uqpay.sdk.config.CashierConfig;
import com.uqpay.sdk.config.MerchantConfig;
import com.uqpay.sdk.config.PaygateConfig;


/**
 * <p>UqpayProperties class.</p>
 *
 * @author zhengwei
 * @version $Id: $Id
 */
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

  /**
   * <p>Getter for the field <code>merchant</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.config.MerchantConfig} object.
   */
  public MerchantConfig getMerchant() {
    return merchant;
  }

  /**
   * <p>Setter for the field <code>merchant</code>.</p>
   *
   * @param merchant a {@link com.uqpay.sdk.config.MerchantConfig} object.
   */
  public void setMerchant(MerchantConfig merchant) {
    this.merchant = merchant;
  }

  /**
   * <p>Getter for the field <code>paygate</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.config.PaygateConfig} object.
   */
  public PaygateConfig getPaygate() {
    return paygate;
  }

  /**
   * <p>Setter for the field <code>paygate</code>.</p>
   *
   * @param paygate a {@link com.uqpay.sdk.config.PaygateConfig} object.
   */
  public void setPaygate(PaygateConfig paygate) {
    this.paygate = paygate;
  }

  /**
   * <p>Getter for the field <code>cashier</code>.</p>
   *
   * @return a {@link com.uqpay.sdk.config.CashierConfig} object.
   */
  public CashierConfig getCashier() {
    return cashier;
  }

  /**
   * <p>Setter for the field <code>cashier</code>.</p>
   *
   * @param cashier a {@link com.uqpay.sdk.config.CashierConfig} object.
   */
  public void setCashier(CashierConfig cashier) {
    this.cashier = cashier;
  }
}
