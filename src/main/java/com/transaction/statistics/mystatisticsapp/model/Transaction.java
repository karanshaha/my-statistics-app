package com.transaction.statistics.mystatisticsapp.model;

public class Transaction {
  private Double amount;
  private Long epochTime;

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Long getEpochTime() {
    return epochTime;
  }

  public void setEpochTime(Long epochTime) {
    this.epochTime = epochTime;
  }

}
