package org.dimalei.pricey.bot.model;

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
@Setter
@NoArgsConstructor
@Table("JOBS")
public class Job {

    @Id
    private Integer id;

    @NotEmpty
    private String title;

    @URL
    private String url;

    @MappedCollection(idColumn = "JOB_ID", keyColumn = "POSITION")
    private List<IntermediateAction> intermediateActions;

    @NotEmpty
    private String targetProperty;

    public Job(String title, Integer id, String url, String attribute) {
        this.title = title;
        this.id = id;
        this.url = url;
        this.targetProperty = attribute;
        this.intermediateActions = new ArrayList<>();
    }

}
