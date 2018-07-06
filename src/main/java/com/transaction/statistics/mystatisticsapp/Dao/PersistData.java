package com.transaction.statistics.mystatisticsapp.Dao;

import com.transaction.statistics.mystatisticsapp.model.Transaction;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is basically used to store the transactions.
 */
public class PersistData {

  static ConcurrentHashMap<String, Transaction> transactionConcurrentHashMap = new ConcurrentHashMap<>();

  public static ConcurrentHashMap<String, Transaction> getTransactionConcurrentHashMap() {
    return transactionConcurrentHashMap;
  }
}
