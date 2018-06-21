package com.customer.statement.processing.service;

import com.customer.statement.processing.dataprovider.TestData;
import com.customer.statement.processing.exception.CSVCustomerStatementProcessingException;
import com.customer.statement.processing.model.Record;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerStatementFinalReportServiceTest {
    @Mock
    private CustomerStatementFinalReportService customerStatementFinalReportService;

    @Test
    public void testExecute() throws CSVCustomerStatementProcessingException {
        List<Record> recordList = TestData.getListOfRecord();
        doNothing().when(customerStatementFinalReportService).writeToCSV(isA(List.class), isA(String.class));
        customerStatementFinalReportService.writeToCSV(recordList, "./upload/records.csv");
        verify(customerStatementFinalReportService, times(1)).writeToCSV(recordList, "./upload/records.csv");
    }
}
