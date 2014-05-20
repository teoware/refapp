package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class DeleteUserAddressInput extends ChangeInput {

    public DeleteUserAddressInput() {
        super();
    }

    public DeleteUserAddressInput(Id id) {
        super(id);
    }
}
