package com.customer.statement.processing.controller;

import com.customer.statement.processing.dataprovider.CustomerStatementProcessingProvider;
import com.customer.statement.processing.exception.CSVCustomerStatementProcessingException;
import com.customer.statement.processing.exception.CustomerStatementProcessingException;
import com.customer.statement.processing.exception.XMLCustomerStatementProcessingException;
import com.customer.statement.processing.model.Record;
import com.customer.statement.processing.service.CustomerStatementFinalReportService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Upload Controller is responsible for routing the request to respective
 * Process class and get the output.
 *
 * @author Maheswaran Venkatraman
 * @date 18 June 2018
 */

@RestController
public class CustomerStatementUploadController {

    private CustomerStatementFinalReportService customerStatementErrorReportService;
    private CustomerStatementProcessingProvider customerStatementProcessingFileProvider;

    @Value("${errorFileName.path}")
    private String errorDataProcessingFilePath;

    @Autowired
    public CustomerStatementUploadController(CustomerStatementFinalReportService customerStatementErrorReportService,
                                             CustomerStatementProcessingProvider customerStatementProcessingFileProvider) {
        this.customerStatementErrorReportService = customerStatementErrorReportService;
        this.customerStatementProcessingFileProvider = customerStatementProcessingFileProvider;
    }

    @RequestMapping("/")
    public String invoke() {
        try {
            Pair<Set<Record>, List<Record>> processedData = customerStatementProcessingFileProvider.getFileLocation();
            customerStatementErrorReportService.writeToCSV(processedData.getRight(), errorDataProcessingFilePath);
            return "Your file has been Processed Successfully!!! Please check the Report.csv file is there any errors."
                    + " You Can find the file in following location Upload location";
        } catch (CSVCustomerStatementProcessingException | XMLCustomerStatementProcessingException e) {
            //Here you should throw the request Response.serverError().entity(new ErrorResponse()).build();
            //so that frontend receive the error note then it will show the exception.
            throw new CustomerStatementProcessingException("This is not a valid file!!!");
        }
    }
}
