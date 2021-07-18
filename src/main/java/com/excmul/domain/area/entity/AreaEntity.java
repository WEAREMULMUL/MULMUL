package com.excmul.domain.area.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        indexes = {
                @Index(name = "INDEX_eup_myeon_dong", columnList = "eupMyeonDong", unique = false)
        },
        uniqueConstraints = {
            @UniqueConstraint(name = "UNIQUE_AREA", columnNames = { "siDo", "siGunGu", "eupMyeonDong" })
})
@Entity
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 시도 명
    @Column(nullable = false, length = 30)
    private String siDo;

    // 시군구 명
    @Column(nullable = false, length = 30)
    private String siGunGu;

    // 읍면동 명
    @Column(nullable = false, length = 20)
    private String eupMyeonDong;
}
