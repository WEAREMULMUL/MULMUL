package com.excmul.interceptor;

import com.excmul.domain.category.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Function;

/*
    Controller에서 String으로 fragement 이름 반환시 Layout에 감싸주는 interceptor 입니다.
    ViewName이 f:로 시작시 Header와 Footer를 분리
*/
@RequiredArgsConstructor
@Component
public class LayoutInterceptor implements SimpleInterceptor {
    private static final String FULL_CONTENT_FLAG = "f:";
    private final CategoryService categoryService;

    @Override
    public String[] includePathPatterns() {
        return new String[] { "/**" };
    }

    @Override
    public String[] excludePathPatterns() {
        return new String[] { "/assets/**"};
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView == null)
            return;

        String viewName = modelAndView.getViewName();
        if (!StringUtils.hasText(viewName))
            return; // TODO :: 오류 처리
        boolean fullContent = isFullContent(viewName);

        modelAndView.setViewName(
                fullContent ? Layout.FULL_CONTENT_LAYOUT.getPath() : Layout.DEFAULT.getPath());
        modelAndView.addObject("content",
                fullContent ? ViewName.FULL_CONTENT_LAYOUT.get(viewName) : ViewName.DEFAULT.get(viewName));

        // Header의 CategoryNode 삽입
        if (!fullContent) {
            modelAndView.addObject("categoryNode", categoryService.findCategoryByLevel(1));
        }
    }

    private boolean isFullContent(String viewName) {
        return viewName.startsWith(FULL_CONTENT_FLAG);
    }

    @Getter
    @AllArgsConstructor
    private enum Layout {
        DEFAULT("layout.html"),
        FULL_CONTENT_LAYOUT("layout-full-content.html");

        private String path;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    private enum ViewName {
        DEFAULT(),
        FULL_CONTENT_LAYOUT((viewName) -> viewName.substring(FULL_CONTENT_FLAG.length()));

        private static final String FRAGMENTS_PATH = "fragments/contents/";
        private Function<String, String> funcTrans = null;

        public String get(String viewName) {
            return prefix(funcTrans == null ? viewName : funcTrans.apply(viewName));
        }

        private String prefix(String viewName) {
            return FRAGMENTS_PATH + viewName;
        }
    }
}
