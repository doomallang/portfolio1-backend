package com.doomole.portfolio.dto.response.finance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResNews {
    private String title;
    private String link;
    private String description;
    private String pubDate;
}
