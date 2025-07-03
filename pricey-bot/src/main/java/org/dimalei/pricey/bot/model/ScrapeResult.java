package org.dimalei.pricey.bot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table("SCRAPE_RESULTS")
public class ScrapeResult {

    @Id
    private Integer id;
    private Long jobId;
    private String result;
}
