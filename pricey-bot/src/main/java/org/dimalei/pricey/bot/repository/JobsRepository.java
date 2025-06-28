package org.dimalei.pricey.bot.repository;

import org.dimalei.pricey.bot.model.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobsRepository extends CrudRepository<Job, Integer> {

}
