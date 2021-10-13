package com.excmul.notice.dto;

import com.excmul.notice.domain.vo.Content;
import com.excmul.notice.domain.vo.Title;
import lombok.Data;

@Data
public class NoticeRegistryDto {
    private Title title;
    private Content content;
}
