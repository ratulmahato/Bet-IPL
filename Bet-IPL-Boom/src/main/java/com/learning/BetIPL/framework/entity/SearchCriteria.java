package com.learning.BetIPL.framework.entity;

public class SearchCriteria {

    private String fieldName;
    private String fieldValue;

    public SearchCriteria() {

    }

    public SearchCriteria(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {

        return fieldName;
    }

    public void setFieldName(String fieldName) {

        this.fieldName = fieldName;
    }

    public String getFieldValue() {

        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {

        this.fieldValue = fieldValue;
    }

}
