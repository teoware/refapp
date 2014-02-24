package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.common.OperationResult;

public class ChangeUserResponseVO {

    private OperationResult result;

    public ChangeUserResponseVO(OperationResult result) {
        this.result = result;
    }

    public OperationResult getResult() {
        return result;
    }

    public void setResult(OperationResult result) {
        this.result = result;
    }
}
