package com.excmul.domain.common;

/**
 * BaseAggregate [interface]
 */

public interface BaseAggregate {

    /**
     *  @Override HashCode
     *
     *  int HASH_CODE_NULL = 0;
     *  int HASH_CODE_PRIME = 31;
     *
     */

    /**
     * @param string
     */
    void validate(String string);

    /**
     * @return
     */
    String toString();

    /**
     * @param object
     * @return boolean equals(Object object);
     */
    /**
     * @return int hashCode();
     */
}