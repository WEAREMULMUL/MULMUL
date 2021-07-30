package com.excmul.area.application;

import com.excmul.area.domain.AreaDTO;
import com.excmul.area.domain.AreaEntity;
import com.excmul.area.domain.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
