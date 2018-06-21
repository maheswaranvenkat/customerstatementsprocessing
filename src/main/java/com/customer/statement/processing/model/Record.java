package com.customer.statement.processing.model;

import com.customer.statement.processing.validation.ValidateEndBalance;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//This Pojo class has been mapped with both CSV and XML. Especially in XML there properties
//has been mapped.

@XmlRootElement(name = "record")
public class Record {
    private String reference;
    private String accountNumber;
    private String description;
    private String startBalance;
    private String mutation;
    private String endBalance;

    public String getReference() {
        return reference;
    }

    @XmlElement(name = "reference")
    public Record setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @XmlElement(name = "accountNumber")
    public Record setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name = "description")
    public Record setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getStartBalance() {
        return startBalance;
    }

    @XmlElement(name = "startBalance")
    public Record setStartBalance(String startBalance) {
        this.startBalance = startBalance;
        return this;
    }

    public String getMutation() {
        return mutation;
    }

    @XmlElement(name = "mutation")
    public Record setMutation(String mutation) {
        this.mutation = mutation;
        return this;
    }

    public String getEndBalance() {
        return endBalance;
    }

    @ValidateEndBalance(min = 0, message = "Amount field should not be empty")
    @NotNull
    @XmlElement(name = "endBalance")
    public Record setEndBalance(String endBalance) {
        this.endBalance = endBalance;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Record that = (Record) o;

        return new EqualsBuilder()
                .append(reference, that.reference)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(reference)
                .toHashCode();
    }
}
