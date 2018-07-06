package com.transaction.statistics.mystatisticsapp.controller;

import com.transaction.statistics.mystatisticsapp.services.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StatisticsControllerTest {

  private MockMvc mvc;

  private TransactionService transactionService;

  @Before
  public void setUp() throws Exception {
    StatisticsController statisticsController = new StatisticsController();
    transactionService = mock(TransactionService.class);
    ReflectionTestUtils.setField(statisticsController, "transactionService", transactionService);
    mvc = MockMvcBuilders.standaloneSetup(statisticsController).build();
  }

  @Test
  public void saveTransactionOlderThanMinTest() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/statistics")
      .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    verify(transactionService, times(1)).getStatistics(anyLong());
  }

}
