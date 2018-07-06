package com.transaction.statistics.mystatisticsapp.controller;

import com.transaction.statistics.mystatisticsapp.model.Transaction;
import com.transaction.statistics.mystatisticsapp.services.TransactionService;
import com.transaction.statistics.mystatisticsapp.util.Util;
import com.transaction.statistics.mystatisticsapp.validator.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

  @Autowired
  TransactionService transactionService;

  @Autowired
  TransactionValidator transactionValidator;

  /**
   * This API is used to create and persist the @{@link Transaction}.
   *
   * @param transaction
   * @return @ResponseEntity
   */
  @RequestMapping(value = "/transactions", method = RequestMethod.POST)
  public ResponseEntity createTransaction(@RequestBody Transaction transaction) {
    boolean isTransactionWithinMin = transactionValidator
      .validateTransactionWithinMin(Util.calculateSecondsBetweenDates(Util.getDate().getTime(), transaction.getEpochTime()));
    transactionService.saveTransaction(transaction);
    if (isTransactionWithinMin) {
      return new ResponseEntity(null, HttpStatus.resolve(201));
    }
    return new ResponseEntity(null, HttpStatus.resolve(204));
  }

}
