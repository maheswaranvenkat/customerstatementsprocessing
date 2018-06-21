package com.customer.statement.processing.dataprovider;

import com.customer.statement.processing.business.CSVCustomerStatementProcessing;
import com.customer.statement.processing.business.XMLCustomerStatementProcessing;
import com.customer.statement.processing.exception.CustomerStatementProcessingException;
import com.customer.statement.processing.model.Record;
import com.customer.statement.processing.utils.FileExtensionFilter;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * This is the Customer Statement Processing File Provider class which serves
 * the file name based on the requeste by the customer in the Yaml Configuration
 * file.
 *
 * @author mahesh
 */

@Component
public class CustomerStatementProcessingProvider {

    private CSVCustomerStatementProcessing csvCustomerStatementProcess;
    private XMLCustomerStatementProcessing xmlCustomerStatementProcess;

    @Value("${defaultFileEnabledExtension}")
    private String defaultFileEnabledExtension;

    @Value("${csvFileName.path}")
    private String csvfileNamePath;

    @Value("${xmlFileName.path}")
    private String xmlfileNamePath;

    @Autowired
    public CustomerStatementProcessingProvider(final CSVCustomerStatementProcessing csvCustomerStatementProcess,
                                               final XMLCustomerStatementProcessing xmlCustomerStatementProcess) {
        this.csvCustomerStatementProcess = csvCustomerStatementProcess;
        this.xmlCustomerStatementProcess = xmlCustomerStatementProcess;
    }

    public Pair<Set<Record>, List<Record>> getFileLocation() {
        FileExtensionFilter extensionFilter = FileExtensionFilter.fromString(defaultFileEnabledExtension);
        switch (extensionFilter) {
            case CSV: {
                return csvCustomerStatementProcess.execute(csvfileNamePath);
            }
            case XML: {
                return xmlCustomerStatementProcess.execute(xmlfileNamePath);
            }
            default: {
                throw new CustomerStatementProcessingException("Customer Doesn't select any related file");
            }
        }
    }
}
