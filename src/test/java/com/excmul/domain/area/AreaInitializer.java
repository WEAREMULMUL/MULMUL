package com.excmul.domain.area;

import com.excmul.domain.category.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class AreaInitializer {
    @Autowired
    private AreaService areaService;

    @Test
    @Rollback(false)
    @Transactional
    public void insertArea() throws IOException {
        List<AreaEntity> areaEntityList = loadAreaData();
        areaService.insertAll(areaEntityList);
    }

    private List<AreaEntity> loadAreaData() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("data/지역.txt");

        Path path = Path.of(classPathResource.getURI());
        List<String> lines = Files.readAllLines(path);

        return lines.stream().map(this::toAreaEntity).collect(Collectors.toList());
    }

    private AreaEntity toAreaEntity(String line) {
        String[] split = line.split("\\|");
        return AreaEntity.builder()
                .siDo(split[0])
                .siGunGu(split[1])
                .eupMyeonDong(split[2])
                .build();
    }
}