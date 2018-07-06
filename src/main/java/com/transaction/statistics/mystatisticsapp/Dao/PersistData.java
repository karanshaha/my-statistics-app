package com.transaction.statistics.mystatisticsapp.Dao;

import com.transaction.statistics.mystatisticsapp.model.Transaction;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is basically used to store the transactions.
 */
public class PersistData {

  private static PersistData persistData = null;
  public ConcurrentHashMap<String, Transaction> transactionConcurrentHashMap;

  private PersistData() {
    transactionConcurrentHashMap = new ConcurrentHashMap<>();
  }

  public static PersistData getInstance() {
    if (persistData == null) {
      synchronized (PersistData.class) {
        if (persistData == null) {
          persistData = new PersistData();
        }
      }
    }
    return persistData;
  }

}
