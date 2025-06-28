package org.dimalei.pricey.bot.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Job {

    @NotEmpty
    private String title;
    private String id;
    @URL
    private String url;
    private List<Action> actions;
    @NotEmpty
    private String targetProperty;

    public Job(String title, String id, String url, String attribute) {
        this.title = title;
        this.id = id;
        this.url = url;
        this.targetProperty = attribute;
        this.actions = new ArrayList<>();
    }

}
