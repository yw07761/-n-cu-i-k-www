package com.example.ontapck.enums;

public enum ProductStatus {
    ACTIVE(0), IN_ACTIVE(1);
    private int value;
    ProductStatus(int i) {
        this.value = i;
    }
    public int getValue() {
        return value;
    }
}