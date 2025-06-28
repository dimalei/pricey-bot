package org.dimalei.pricey.bot.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.dimalei.pricey.bot.model.Job;
import org.springframework.stereotype.Service;

@Service
public class JobsService {

    private Map<String, Job> db = new HashMap<>() {
        {
            put("123", new Job("hosen", "123", "https://www.baechli-bergsport.ch",
                    "/html/body/main/div[1]/div[1]/div[2]/article[2]/div[1]/form/div[4]/span[2]"));
        }
    };

    public Collection<Job> get() {
        return db.values();
    }

    public Job get(String id) {
        return db.get(id);
    }

    public Job remove(String id) {
        return db.remove(id);
    }

    public Job save(Job job) {
        job.setId(UUID.randomUUID().toString());
        db.put(job.getId(), job);
        return job;
    }

}