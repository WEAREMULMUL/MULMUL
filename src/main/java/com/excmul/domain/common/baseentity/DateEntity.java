package com.excmul.domain.common.baseentity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class DateEntity {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
    private Date registerDate;
}
