package com.teoware.refapp.model.user;

import org.joda.time.DateTime;

import com.teoware.refapp.model.enums.Status;

public class UserStatus {

    private Status status;
    private DateTime created;
    private DateTime modified;

    public UserStatus() {
    }

    public UserStatus(Status status, DateTime created, DateTime modified) {
        this.status = status;
        this.created = created;
        this.modified = modified;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DateTime getCreated() {
        return created;
    }

    public void setCreated(DateTime created) {
        this.created = created;
    }

    public DateTime getModified() {
        return modified;
    }

    public void setModified(DateTime modified) {
        this.modified = modified;
    }
}
