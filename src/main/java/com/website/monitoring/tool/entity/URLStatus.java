package com.website.monitoring.tool.entity;

public enum URLStatus {

    OK("OK"), WARNING("Warning"), CRITICAL("Critical"), NOT_VALIDATED("Not Validated");

    private String label;

    URLStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}