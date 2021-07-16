package com.excmul.domain.area;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uniqueArea", columnNames = { "siDo", "siGunGu", "eupMyeonDong" })
})
@Entity
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 시도 명
    @Column
    private String siDo;

    // 시군구 명
    @Column
    private String siGunGu;

    // 읍면동 명
    @Column
    private String eupMyeonDong;
}
