package org.dimalei.pricey.bot;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Job {

    private String id;
    private String url;
    private List<Action> actions;
    private String attribute;

    public Job(String id, String url, String attribute) {
        this.id = id;
        this.url = url;
        this.attribute = attribute;
        this.actions = new ArrayList<>();
    }

}
