package com.transaction.statistics.mystatisticsapp.controller;

import com.transaction.statistics.mystatisticsapp.model.Statistics;
import com.transaction.statistics.mystatisticsapp.services.TransactionService;
import com.transaction.statistics.mystatisticsapp.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

  @Autowired
  TransactionService transactionService;

  /**
   * This API is used to get all the transactions which are persisted
   * It also returns the @{@link Statistics} having min,max,avg,sum and count
   * of only those transactions that are within the range of 60 secs when we
   * hit the API.
   *
   * @return @{@link Statistics}
   */
  @RequestMapping(value = "/statistics", method = RequestMethod.GET)
  public @ResponseBody Statistics createTransaction() {
    Long currentEpochTime = Util.getDate().getTime();
    return transactionService.getStatistics(currentEpochTime);

  }

}
