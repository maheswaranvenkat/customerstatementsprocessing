package com.customer.statement.processing.business;

import com.customer.statement.processing.exception.CSVCustomerStatementProcessingException;
import com.customer.statement.processing.model.Record;
import com.customer.statement.processing.utils.CommonUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * CSVCustomer Statement Process is responsible for processing the data from CSV file.
 *
 * @author Maheswaran Venkatraman
 * @date 18 June 2018
 */

@Component
public class CSVCustomerStatementProcessing {

    private static Function<String, Record> mapToCustomerStatement = (line) -> {
        Record record = new Record();
        String[] customerStatementFunction = line.split(CommonUtils.CSV_SEPARATOR);
        record.setReference(customerStatementFunction[0]);
        record.setAccountNumber(customerStatementFunction[1]);
        record.setDescription(customerStatementFunction[2]);
        record.setStartBalance(customerStatementFunction[3]);
        record.setMutation(customerStatementFunction[4]);
        record.setEndBalance(customerStatementFunction[5]);
        return record;
    };

    public Pair<Set<Record>, List<Record>> execute(final String csvFile) throws CSVCustomerStatementProcessingException {
        List<Record> customerStatementRecords = new ArrayList<>();
        try (final InputStream is = new FileInputStream(new File(csvFile));
             final BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            customerStatementRecords = br.lines().skip(1)
                    .map(mapToCustomerStatement)
                    .collect(toList());
        } catch (IOException e) {
            throw new CSVCustomerStatementProcessingException("CSV Processing Exception" + e.getMessage());
        }
        return CommonUtils.resultSet(customerStatementRecords);
    }
}
