package com.excmul.common.domain;

public interface BaseAggregate {
    void validate(String string);

    String toString();

    boolean equals(Object object);

    int hashCode();
}