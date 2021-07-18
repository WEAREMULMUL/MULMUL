package com.excmul.domain.area.dto;

import com.excmul.domain.area.entity.AreaEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
