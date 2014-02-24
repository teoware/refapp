package com.teoware.refapp.batch.schedule;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.teoware.refapp.batch.ActivationBatch;

@Startup
@Singleton
public class ActivationBatchScheduler extends BatchScheduler {

    @Inject
    private ActivationBatch activationBatch;

    @PostConstruct
    @Override
    public void setup() {
        setup(activationBatch, new Schedule("*", "*/15", "0"));
    }
}
