package com.transaction.statistics.mystatisticsapp.validator;

public interface TransactionValidator {
   boolean validateTransactionWithinMin(int diffInSeconds);
}
