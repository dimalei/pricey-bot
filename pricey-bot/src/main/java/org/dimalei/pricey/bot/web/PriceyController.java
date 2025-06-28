package org.dimalei.pricey.bot.web;

import java.util.Collection;

import org.dimalei.pricey.bot.model.Job;
import org.dimalei.pricey.bot.service.JobsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
public class PriceyController {

    private final JobsService jobsService;

    public PriceyController(JobsService jobsService) {
        this.jobsService = jobsService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/jobs")
    public Collection<Job> get() {
        return jobsService.get();
    }

    @GetMapping("/jobs/{id}")
    public Job get(@PathVariable String id) {
        Job job = jobsService.get(id);
        if (job == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return job;
    }

    @DeleteMapping("/jobs/{id}")
    public Job deleteJob(@PathVariable String id) {
        Job deletedJob = jobsService.remove(id);
        if (deletedJob == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return deletedJob;
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody @Valid Job job) {
        jobsService.save(job);
        return ResponseEntity.ok("Job created successfully!");
    }

}
