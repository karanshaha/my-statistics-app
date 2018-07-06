package com.transaction.statistics.mystatisticsapp.services;

import com.transaction.statistics.mystatisticsapp.model.Statistics;
import com.transaction.statistics.mystatisticsapp.model.Transaction;

public interface TransactionService {

  /**
   * This method saves the @{@link Transaction} Object.
   * @param transaction
   */
  void saveTransaction(Transaction transaction);

  /**
   * This method is used to get all the transactions which are persisted
   * It also returns the @{@link Statistics} having min,max,avg,sum and count
   * of only those transactions that are within the range of 60 secs.
   *
   * @param epochTime time in milliseconds
   * @return @{@link Statistics}
   */
  Statistics getStatistics(Long epochTime);
}
