package com.vivareal.app.view.controller

import com.vivareal.app.service.JobService
import com.vivareal.app.view.resource.JobsResource
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping

import static org.springframework.http.MediaType.*
import static org.springframework.web.bind.annotation.RequestMethod.*

/**
 * @author Andrés Amado
 */
@Slf4j
@Controller
@RequestMapping("/jobs")
class JobsViewController extends ViewController {

    @Autowired
    private JobService jobService
    @Value('${request.max.jobs}')
    private int maxJobs

    private static final String PAGE_JOBS = "jobs"

    @RequestMapping
    String getExecutions(final ModelMap model) {
        updatedView(model, PAGE_JOBS)
    }

    @RequestMapping(value = "/api", method = GET, produces = APPLICATION_JSON_VALUE)
    HttpEntity<JobsResource> getJobs() {
        new ResponseEntity<>(new JobsResource(jobs: jobService.getJobs(maxJobs)), HttpStatus.OK)
    }

}
