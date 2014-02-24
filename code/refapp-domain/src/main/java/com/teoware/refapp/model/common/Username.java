package com.teoware.refapp.model.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.teoware.refapp.model.base.BaseBody;
import com.teoware.refapp.model.util.ValidationRegex;

public class Username extends BaseBody {

    @NotNull
    @Size(min = 3, max = 20)
    @Pattern(regexp = ValidationRegex.USERNAME)
    private String username;

    public Username() {
    }

    public Username(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
