package org.dimalei.pricey.bot.service;

import org.dimalei.pricey.bot.model.Job;
import org.dimalei.pricey.bot.repository.JobsRepository;
import org.springframework.stereotype.Service;

@Service
public class JobsService {

    private final JobsRepository jobsRepository;

    public JobsService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public Iterable<Job> get() {
        return jobsRepository.findAll();
    }

    public Job get(Integer id) {
        return jobsRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        jobsRepository.deleteById(id);
    }

    public Job save(Job job) {
        jobsRepository.save(job);
        return job;
    }

}