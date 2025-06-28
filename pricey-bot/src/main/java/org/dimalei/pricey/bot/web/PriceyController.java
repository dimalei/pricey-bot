package org.dimalei.pricey.bot.web;

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
    public Iterable<Job> get() {
        return jobsService.get();
    }

    @GetMapping("/jobs/{id}")
    public Job get(@PathVariable Integer id) {
        Job job = jobsService.get(id);
        if (job == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return job;
    }

    @DeleteMapping("/jobs/{id}")
    public void deleteJob(@PathVariable Integer id) {
        jobsService.remove(id);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody @Valid Job job) {
        jobsService.save(job);
        return ResponseEntity.ok("Job created successfully!");
    }

}
