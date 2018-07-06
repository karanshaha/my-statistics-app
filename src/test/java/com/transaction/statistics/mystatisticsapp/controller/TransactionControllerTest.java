package com.transaction.statistics.mystatisticsapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.transaction.statistics.mystatisticsapp.model.Transaction;
import com.transaction.statistics.mystatisticsapp.services.TransactionService;
import com.transaction.statistics.mystatisticsapp.validator.TransactionValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionControllerTest {

  private MockMvc mvc;

  private TransactionService transactionService;

  private TransactionValidator transactionValidator;

  @Before
  public void setUp() throws Exception {
    TransactionController transactionController = new TransactionController();
    transactionService = mock(TransactionService.class);
    transactionValidator = mock(TransactionValidator.class);
    ReflectionTestUtils.setField(transactionController, "transactionService", transactionService);
    ReflectionTestUtils.setField(transactionController, "transactionValidator", transactionValidator);
    mvc = MockMvcBuilders.standaloneSetup(transactionController).build();
  }

  @Test
  public void saveTransactionOlderThanMinTest() throws Exception {
    ObjectWriter ow = new ObjectMapper().writer();
    String content = ow.writeValueAsString(createTransaction());
    mvc.perform(MockMvcRequestBuilders.post("/transactions").content(content)
      .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(204));
    verify(transactionService, times(1)).saveTransaction(Mockito.any(Transaction.class));
    verify(transactionValidator, times(1)).validateTransactionWithinMin(anyInt());
  }

 @Test
  public void saveTransactionWithinMinTest() throws Exception {
    TimeZone tz = TimeZone.getTimeZone("UTC");
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    Calendar cal = Calendar.getInstance(tz);
    Date d = cal.getTime();
    Transaction transaction = createTransaction();
    transaction.setEpochTime(d.getTime());
    ObjectWriter ow = new ObjectMapper().writer();
    String content = ow.writeValueAsString(transaction);
    Mockito.when(transactionValidator.validateTransactionWithinMin(anyInt())).thenReturn(true);
    mvc.perform(MockMvcRequestBuilders.post("/transactions").content(content)
      .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(201));
   verify(transactionService, times(1)).saveTransaction(Mockito.any(Transaction.class));
   verify(transactionValidator, times(1)).validateTransactionWithinMin(anyInt());
  }

  private Transaction createTransaction() {
    Transaction transaction = new Transaction();
    transaction.setAmount(12D);
    transaction.setEpochTime(1530686757657L);
    return transaction;
  }
}
