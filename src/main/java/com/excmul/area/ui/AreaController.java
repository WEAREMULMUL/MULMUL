package com.excmul.area.ui;

import com.excmul.area.application.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SuppressWarnings("SpringMVCViewInspection")
@RequiredArgsConstructor
@RequestMapping("area")
@Controller
public class AreaController {
    private final AreaService areaService;

    @GetMapping("select")
    public String selectArea() {
        return "area/select";
    }
}
