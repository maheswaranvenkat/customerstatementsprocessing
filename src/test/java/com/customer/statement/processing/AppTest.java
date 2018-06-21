package com.customer.statement.processing;

import com.customer.statement.processing.controller.CustomerStatementUploadController;
import com.customer.statement.processing.dataprovider.CustomerStatementProcessingProvider;
import com.customer.statement.processing.dataprovider.TestData;
import com.customer.statement.processing.model.Record;
import com.customer.statement.processing.service.CustomerStatementFinalReportService;
import com.customer.statement.processing.utils.FileExtensionFilter;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({FileExtensionFilter.class})
public class AppTest {
	@Mock
	CustomerStatementFinalReportService customerStatementFinalReportService;
	@Mock
	CustomerStatementProcessingProvider customerStatementProcessingProvider;
	@InjectMocks
	CustomerStatementUploadController customerStatementUploadController;
	
    @Test
    public void testApp() {
    	Pair<Set<Record>, List<Record>> resultSet = TestData.getSetListPair();
		List<Record> recordList = TestData.getListOfRecord();
    	Mockito.when(customerStatementProcessingProvider.getFileLocation()).thenReturn(resultSet);
		String result = customerStatementUploadController.invoke();
		assertNotNull(resultSet);
    	assertEquals(result, "Your file has been Processed Successfully!!! Please check the Report.csv file is there any errors. "
                + "You Can find the file in following location Upload location");
    }
}
