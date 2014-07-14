package com.vivareal.app.service

import com.vivareal.app.model.Job
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

/**
 * @author Andrés Amado
 */
@Slf4j
@Service
class JobService {

    @Value('${external.jobs.url}')
    private String externalJobs
    @Autowired
    private RestClient client

    List<Job> getJobs(final int max) {
        List<Job> jobs = []
        client.get(externalJobs).jobs.each {
            jobs.add(new Job(
                    name: it.name,
                    url: it.url,
                    color: it.color
            ))
        }
        Collections.shuffle(jobs)
        if (jobs.size() > max) {
            jobs = jobs[1..max]
        }

        jobs
    }

}