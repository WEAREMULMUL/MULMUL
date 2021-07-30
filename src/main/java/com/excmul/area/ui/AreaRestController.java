package com.excmul.area.ui;

import com.excmul.area.application.AreaService;
import com.excmul.area.domain.AreaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("area")
@RestController
public class AreaRestController {
    private final AreaService areaService;

    @GetMapping("{eupMeyonDong}")
    public List<AreaDTO> findArea(@PathVariable("eupMeyonDong") String eupMeyonDong) {
        if (!StringUtils.hasText(eupMeyonDong))
            return Collections.emptyList();
        return areaService.findArea(eupMeyonDong);
    }
}
