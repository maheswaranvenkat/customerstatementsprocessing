package com.customer.statement.processing.business;

import com.customer.statement.processing.dataprovider.TestData;
import com.customer.statement.processing.exception.XMLCustomerStatementProcessingException;
import com.customer.statement.processing.model.Record;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;



@RunWith(MockitoJUnitRunner.class)
public class XMLCustomerStatementProcessTest {

    @InjectMocks
    private XMLCustomerStatementProcessing xmlCustomerStatementProcess;

    @Test
    public void testExecute() throws XMLCustomerStatementProcessingException {
        Pair<Set<Record>, List<Record>> resultSet = TestData.getSetListPair();
        assertEquals(resultSet, xmlCustomerStatementProcess.execute("./upload/records-test.xml"));
     }
}
