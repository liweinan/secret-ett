package com.redhat.wildbee.json.workflow;

/**
 * Created by dcheung on 11/27/14.
 */
public class NewWorkflow {
    private long currentStatusId;
    private long[] nextStatusesId;

    public long getCurrentStatusId() {
        return currentStatusId;
    }

    public void setCurrentStatusId(long currentStatusId) {
        this.currentStatusId = currentStatusId;
    }

    public long[] getNextStatusesId() {
        return nextStatusesId;
    }

    public void setNextStatusesId(long[] nextStatusesId) {
        this.nextStatusesId = nextStatusesId;
    }
}
