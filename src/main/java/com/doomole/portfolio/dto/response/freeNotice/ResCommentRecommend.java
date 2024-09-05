package com.doomole.portfolio.dto.response.freeNotice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResCommentRecommend {
    private long recommendIdx;
    private long commentIdx;
    private long accountIdx;
}
