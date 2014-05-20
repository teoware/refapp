package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.user.UserAddress;

public class CreateUserAddressInput extends ChangeInput {

    private UserAddress userAddress;

    public CreateUserAddressInput() {
        super();
    }

    public CreateUserAddressInput(Id id, UserAddress userAddress) {
        super(id);
        this.userAddress = userAddress;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }
}
