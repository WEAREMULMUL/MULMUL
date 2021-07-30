package com.excmul.area.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AreaDTO {
    private int id;

    private String siDo;

    private String siGunGu;

    private String eupMyeonDong;

    public static AreaDTO of(AreaEntity areaEntity) {
        return AreaDTO.builder()
                .id(areaEntity.getId())
                .siDo(areaEntity.getSiDo())
                .siGunGu(areaEntity.getSiGunGu())
                .eupMyeonDong(areaEntity.getEupMyeonDong()).build();
    }
}
