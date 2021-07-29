package com.excmul.member.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {

    @Column(name = "member_term_Service")
    private boolean termService;

    @Column(name = "member_term_Privacy")
    private boolean termPrivacy;

    @Column(name = "member_term_location")
    private boolean termLocation;

    public boolean isTermService() {
        return termService;
    }

    public boolean isTermPrivacy() {
        return termPrivacy;
    }

    public boolean isTermLocation() {
        return termLocation;
    }
}
