package com.revature.dto;

import java.io.InputStream;
import java.sql.Time;
import java.util.Objects;
import com.revature.model.User;

public class AddReimbursementDTO {

    private String description; //names some descriptive reason for the reimbursement
    private InputStream receipt; //provides some receipt or form of support for reimb.

    Time re

    public AddReimbursementDTO(String description, InputStream image) {
        this.description = description;
        this.receipt = receipt;
    }

   // -- GETTERS AND SETTERS -- //

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getReceipt() {
        return receipt;
    }

    public void setReceipt(InputStream receipt) {
        this.receipt = receipt;
    }

    // --EQUALS AND HASHCODE -- //

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddReimbursementDTO that = (AddReimbursementDTO) o;
        return Objects.equals(description, that.description) && Objects.equals(receipt, that.receipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, receipt);
    }

    @Override
    public String toString() {
        return "AddReimbursementDTO{" +
                "description='" + description + '\'' +
                ", image=" + receipt +
                '}';
    }
}
