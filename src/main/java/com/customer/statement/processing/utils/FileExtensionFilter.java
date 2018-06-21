package com.customer.statement.processing.utils;

import org.apache.commons.lang.StringUtils;

import java.util.EnumSet;

public enum FileExtensionFilter {
    CSV("csv"),
    XML("xml");

    private String code;

    FileExtensionFilter(String code) {
        this.code = new String(code);
    }

    public static FileExtensionFilter fromString(String fileNameExtension) {
        return EnumSet.allOf(FileExtensionFilter.class)
                .stream()
                .filter(value -> StringUtils.equals(value.getCode(), fileNameExtension))
                .findFirst()
                .orElse(null);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
