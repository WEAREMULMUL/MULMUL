package com.excmul.domain.user.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {

    @Column
    private boolean termsService;

    @Column
    private boolean termsPrivacy;

    @Column
    private boolean termsLocation;

}
