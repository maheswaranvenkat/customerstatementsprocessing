package com.customer.statement.processing.business;

import com.customer.statement.processing.exception.XMLCustomerStatementProcessingException;
import com.customer.statement.processing.model.Record;
import com.customer.statement.processing.model.Records;
import com.customer.statement.processing.utils.CommonUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * XMLCustomer Statement Process is responsible for processing the data from XML file.
 *
 * @author Maheswaran Venkatraman
 * @date 18 June 2018
 */

@Component
public class XMLCustomerStatementProcessing {

    public Pair<Set<Record>, List<Record>> execute(final String xmlFile) throws XMLCustomerStatementProcessingException {
        Records customerStatementRecords = new Records();
        try (final InputStream is = new FileInputStream(new File(xmlFile))) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            customerStatementRecords = (Records) jaxbUnmarshaller.unmarshal(is);
        } catch (JAXBException | IOException e) {
            throw new XMLCustomerStatementProcessingException("CSV Processing Exception" + e.getMessage());
        }
        return CommonUtils.resultSet(customerStatementRecords.getRecords());
    }
}
