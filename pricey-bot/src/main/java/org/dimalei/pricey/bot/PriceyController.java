package org.dimalei.pricey.bot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PriceyController {

    private Map<Long, Job> db = new HashMap<>() {
        {
            put(123L, new Job(Long.valueOf(123), "https://www.baechli-bergsport.ch",
                    "/html/body/main/div[1]/div[1]/div[2]/article[2]/div[1]/form/div[4]/span[2]"));
        }
    };

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/jobs")
    public Collection<Job> get() {
        return db.values();
    }

    @GetMapping("/jobs/{id}")
    public Job get(@PathVariable String id) {

        System.out.println(db);

        Job job = db.get(Long.valueOf(id));
        if (job == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return job;
    }

    @DeleteMapping("/jobs/{id}")
    public Job deleteJob(@PathVariable String id) {
        Job deletedJob = db.remove(Long.valueOf(id));
        if (deletedJob == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return deletedJob;
    }

}
