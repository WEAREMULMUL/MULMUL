package com.excmul.domain.common;

public interface BaseAggregate {

    // int HASH_CODE_NULL = 0;
    // int HASH_CODE_PRIME = 31;

    void validate(String string);

    String toString();

    boolean equals(Object object);

    int hashCode();
}