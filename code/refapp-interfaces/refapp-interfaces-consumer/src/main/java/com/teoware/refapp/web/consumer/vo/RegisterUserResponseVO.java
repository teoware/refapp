package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.common.OperationResult;

public class RegisterUserResponseVO {

    private OperationResult result;

    public RegisterUserResponseVO(OperationResult result) {
        this.result = result;
    }

    public OperationResult getResult() {
        return result;
    }

    public void setResult(OperationResult result) {
        this.result = result;
    }
}
