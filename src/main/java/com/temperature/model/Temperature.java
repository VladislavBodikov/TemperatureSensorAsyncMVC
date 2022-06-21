package com.temperature.model;

public final class Temperature {
    private final double value;

    public Temperature(double temperature) {
        this.value = temperature;
    }

    public double getValue() {
        return value;
    }
}
