package com.excmul.interceptor;

import com.excmul.domain.category.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.BiPredicate;

/*
    Controller에서 String으로 fragement 이름 반환시 Layout에 감싸주는 interceptor 입니다.
    ViewName이 f:로 시작시 Header와 Footer를 분리
*/
@RequiredArgsConstructor
@Component
public class LayoutInterceptor implements SimpleInterceptor {
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
        validate(modelAndView);

        String viewName = modelAndView.getViewName();

        Layout layout = Layout.of(request, modelAndView);
        layout.modify(modelAndView, viewName);

        // Header의 CategoryNode 삽입
        if (layout.isInsertHeader() && !modelAndView.getModelMap().containsKey("categoryNode")) {
            modelAndView.addObject("categoryNode", categoryService.findCategoryByLevel(1));
        }
    }

    private void validate(ModelAndView modelAndView) {
        String viewName = modelAndView.getViewName();
        if (!StringUtils.hasText(viewName))
            throw new RuntimeException("TODO"); // TODO :: 오류 처리
    }

    @Getter
    @AllArgsConstructor
    private enum Layout {
        AJAX(
                (request, modelAndView) -> {
                    String xRequestWith = request.getHeader("X-Requested-With");
                    return StringUtils.hasText(xRequestWith) && xRequestWith.equals("XMLHttpRequest");
                },
                false),

        FULL_CONTENT_LAYOUT("layout-full-content.html",
                (request, modelAndView) -> modelAndView.getViewName().startsWith(Layout.FULL_CONTENT_FLAG),
                false),


        DEFAULT("layout.html",
                (request, modelAndView) -> false, // 모든 View Mather가 false 반환 시 최종 선택
                true);

        private static final String FRAGMENTS_PATH = "fragments/contents/";
        private static final String FULL_CONTENT_FLAG = "f:";

        /* Layout Path */
        private String path;
        /* of 메소드에서 matcher의 반환이 true인 Layout 반환 */
        private BiPredicate<HttpServletRequest, ModelAndView> matcher;
        /* CategoryNode 자동 삽입 여부 */
        private Boolean insertHeader;

        Layout(BiPredicate<HttpServletRequest, ModelAndView> matcher, boolean insertHeader) {
            this(null, matcher, insertHeader);
        }

        public void modify(ModelAndView modelAndView, String viewName) {
            if (path != null) {
                modelAndView.setViewName(getPath());
                modelAndView.addObject("content", getWrapViewName(viewName));
            } else {
                modelAndView.setViewName(getWrapViewName(viewName));
            }
        }

        private String getWrapViewName(String viewName) {
            int cutIndex = viewName.indexOf(":");
            if (cutIndex > -1)
                viewName = viewName.substring(cutIndex + 1);
            return contentPrefix(viewName);
        }

        private String contentPrefix(String viewName) {
            return FRAGMENTS_PATH + viewName;
        }

        public boolean isInsertHeader() {
            return insertHeader;
        }

        public static Layout of(HttpServletRequest request, ModelAndView modelAndView) {
            for (Layout iLayout : values()) {
                if (iLayout.matcher.test(request, modelAndView))
                    return iLayout;
            }
            return Layout.DEFAULT;
        }
    }
}
