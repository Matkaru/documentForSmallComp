package org.example.enums;

public enum Vat {
    ZERO(0),
    FIVE(5),
    EIGHT(8),
    TWO_THREE(23);


    private final long  value;

    Vat (long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
