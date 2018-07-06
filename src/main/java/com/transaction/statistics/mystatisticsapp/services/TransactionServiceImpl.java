package com.transaction.statistics.mystatisticsapp.services;

import com.transaction.statistics.mystatisticsapp.Dao.PersistData;
import com.transaction.statistics.mystatisticsapp.model.Statistics;
import com.transaction.statistics.mystatisticsapp.model.Transaction;
import com.transaction.statistics.mystatisticsapp.util.Util;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Random;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Override
  public void saveTransaction(Transaction transaction) {
    Random r = new Random();
    PersistData.getInstance().transactionConcurrentHashMap.put(r.toString(), transaction);
  }

  @Override
  public Statistics getStatistics(Long currentEpochTime) {

    Collection<Transaction> values = PersistData.getInstance().transactionConcurrentHashMap.values();
    return populateStatistics(currentEpochTime, values);
  }

  private Statistics populateStatistics(Long currentEpochTime, Collection<Transaction> values) {
    Statistics statisticsData = new Statistics();
    Long counter = 0L;
    Double sum = 0D;
    Double max = Double.MIN_VALUE;
    Double min = Double.MAX_VALUE;
    Double avg = 0D;
    boolean maxChanged = false;
    boolean minChanged = false;
    for (Transaction transaction : values) {
      int diffInSeconds = Util.calculateSecondsBetweenDates(currentEpochTime, transaction.getEpochTime());
      if (diffInSeconds > 0 && diffInSeconds <= 60) {
        counter++;
        sum = sum + transaction.getAmount();
        if (max < transaction.getAmount()) {
          max = transaction.getAmount();
          maxChanged = true;
        }
        if (min > transaction.getAmount()) {
          min = transaction.getAmount();
          minChanged = true;
        }
      }
    }
    if (sum > 0) {
      avg = sum / counter;
    }
    if (maxChanged) {
      statisticsData.setMax(max);
    } else {
      statisticsData.setMax(0D);
    }
    if (minChanged) {
      statisticsData.setMin(min);
    } else {
      statisticsData.setMin(0D);
    }
    statisticsData.setAvg(avg);
    statisticsData.setSum(sum);
    statisticsData.setCount(counter);
    return statisticsData;
  }
}
