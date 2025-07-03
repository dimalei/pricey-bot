package org.dimalei.pricey.bot.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Table("JOBS")
public class Job {

    @Id
    private Integer id;

    @NotEmpty
    @Setter
    private String title;

    @NotEmpty
    @Setter
    private LocalTime executionTime;

    @URL
    @NotEmpty
    @Setter
    private String url;

    @MappedCollection(idColumn = "JOB_ID", keyColumn = "POSITION")
    @Setter
    private List<IntermediateAction> intermediateActions;

    @NotEmpty
    @Setter
    private String targetProperty;

    @NotEmpty
    @Setter
    private boolean enabled;

    public Job(String title, Integer id, String url, String attribute) {
        this.title = title;
        this.id = id;
        this.url = url;
        this.targetProperty = attribute;
        this.intermediateActions = new ArrayList<>();
    }

}
