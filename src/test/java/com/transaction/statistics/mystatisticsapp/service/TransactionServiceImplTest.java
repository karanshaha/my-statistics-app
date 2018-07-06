package com.transaction.statistics.mystatisticsapp.service;

import com.transaction.statistics.mystatisticsapp.Dao.PersistData;
import com.transaction.statistics.mystatisticsapp.model.Statistics;
import com.transaction.statistics.mystatisticsapp.model.Transaction;
import com.transaction.statistics.mystatisticsapp.services.TransactionService;
import com.transaction.statistics.mystatisticsapp.services.TransactionServiceImpl;
import com.transaction.statistics.mystatisticsapp.util.Util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Util.class)
public class TransactionServiceImplTest {

  TransactionService transactionService;

  @Before
  public void setUp() throws Exception {
    transactionService = new TransactionServiceImpl();
    PersistData.getInstance().transactionConcurrentHashMap.put("1", createTransaction());
  }

  @Test
  public void getStatisticsWithinMinTest() {
    PowerMockito.mockStatic(Util.class);
    BDDMockito.given(Util.calculateSecondsBetweenDates(Mockito.anyLong(), Mockito.anyLong())).willReturn(12);
    Statistics statistics = transactionService.getStatistics(1L);
    Assert.assertEquals(statistics.getSum().doubleValue(), 12, 0);
    Assert.assertEquals(statistics.getCount(), 1, 0);
  }

  @Test
  public void getStatisticsOlderThanMinTest() {
    PowerMockito.mockStatic(Util.class);
    BDDMockito.given(Util.calculateSecondsBetweenDates(Mockito.anyLong(), Mockito.anyLong())).willReturn(70);
    Statistics statistics = transactionService.getStatistics(1L);
    Assert.assertEquals(statistics.getSum().doubleValue(), 0, 0);
    Assert.assertEquals(statistics.getCount(), 0, 0);
  }

  private Transaction createTransaction() {
    Transaction transaction = new Transaction();
    transaction.setAmount(12D);
    transaction.setEpochTime(1530686757657L);
    return transaction;
  }

}
