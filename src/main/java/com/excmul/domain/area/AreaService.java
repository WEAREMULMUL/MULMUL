package com.excmul.domain.area;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AreaService {
    private final AreaRepository areaRepository;

    public void insertAll(List<AreaEntity> areaEntityList) {
        areaRepository.saveAll(areaEntityList);
    }
}
