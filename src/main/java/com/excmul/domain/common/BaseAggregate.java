package com.excmul.domain.common;

public interface BaseAggregate {

    int HASH_CODE_NULL = 0;
    int HASH_CODE_PRIME = 31;

    void validate(String str);

    String toString();

    boolean equals(Object obj);

    int hashCode();
}