package com.transaction.statistics.mystatisticsapp.validator;

import org.springframework.stereotype.Component;

@Component
public class TransactionValidatorImpl implements TransactionValidator{

  /**
   * Method returns boolean value depending on the diff in seconds.
   * @param diffInSec
   * @return boolean
   */
  public boolean validateTransactionWithinMin(int diffInSec) {
    if (diffInSec > 0 && diffInSec <= 60) {
      return true;
    }
    return false;
  }
}
