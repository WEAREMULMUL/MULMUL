package com.excmul.domain.area;

import com.excmul.domain.area.dto.AreaDTO;
import com.excmul.domain.area.entity.AreaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AreaService {
    private final AreaRepository areaRepository;

    public void insertAll(List<AreaEntity> areaEntityList) {
        areaRepository.saveAll(areaEntityList);
    }

    public List<AreaDTO> findArea(String eupMeyonDong) {
        return areaRepository.findAllByEupMyeonDongContains(eupMeyonDong)
                .stream()
                .map(AreaDTO::of)
                .collect(Collectors.toList());
    }
}
