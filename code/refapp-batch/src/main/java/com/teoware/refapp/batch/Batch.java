package com.teoware.refapp.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.batch.job.BatchJob;
import com.teoware.refapp.batch.util.BatchJobHandler;
import com.teoware.refapp.schedule.Runnable;

public abstract class Batch extends Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Batch.class);

    private BatchJobHandler jobHandler = new BatchJobHandler();

    public String name() {
        return this.getClass().getSimpleName();
    }

    protected abstract void setup();

    @Override
    public void run() {
        if (jobHandler.count() > 0) {
            for (BatchJob job : jobHandler.list()) {
                runJob(job);
            }
        } else {
            LOG.warn("Job list empty. No jobs will run");
        }
    }

    protected void addJob(BatchJob job) {
        if (job != null) {
            LOG.info("Adding new job {} to batch {}", job.name(), name());
            jobHandler.add(job);
        } else {
            LOG.warn("Unable to add batch job which is null");
        }
    }

    protected void runJob(BatchJob job) {
        if (job != null) {
            LOG.info("Batch {} running job {}", name(), job.name());
            job.run();
        } else {
            LOG.warn("Unable to run batch job which is null");
        }
    }
}
