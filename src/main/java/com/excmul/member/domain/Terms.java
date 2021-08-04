package com.excmul.member.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// :: TODO
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {

    @Column(nullable = false)
    private boolean termService;

    @Column(nullable = false)
    private boolean termPrivacy;

    @Column(nullable = false)
    private boolean termLocation;

    public void Terms(boolean termService, boolean termPrivacy, boolean termLocation) {
        this.termService = termService;
        this.termPrivacy = termPrivacy;
        this.termLocation = termLocation;
    }

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
