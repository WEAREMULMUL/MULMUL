package com.excmul.controller;

import com.excmul.domain.area.AreaService;
import com.excmul.domain.area.dto.AreaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("ajax")
@RestController
public class AjaxController {
    private final AreaService areaService;

    @GetMapping("area/{eupMeyonDong}")
    public List<AreaDTO> findArea(@PathVariable("eupMeyonDong") String eupMeyonDong) {
        if (!StringUtils.hasText(eupMeyonDong))
            return Collections.emptyList();
        return areaService.findArea(eupMeyonDong);
    }
}
