package com.excmul.domain.user.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Getter
@Embeddable
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {

    @Column
    private boolean termsService;

    @Column
    private boolean termsPrivacy;

    @Column
    private boolean termsLocation;

}
