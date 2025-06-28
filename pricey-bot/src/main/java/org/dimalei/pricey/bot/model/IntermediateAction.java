package org.dimalei.pricey.bot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table("INTERMEDIATE_ACTIONS")
public class IntermediateAction {

    @Id
    private Long id;

    private Long jobId;
    private Integer position;
    private String instruction;

}
