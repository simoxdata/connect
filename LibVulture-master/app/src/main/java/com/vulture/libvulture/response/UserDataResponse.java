package com.vulture.libvulture.response;


import com.vulture.libvulture.Config;
import com.vulture.libvulture.model.VultureUserData;

/**
 * Created by han on 2016/11/28.
 */

public class UserDataResponse implements ApiResponse {
    private int code;
    private VultureUserData message;

    public int getCode() {
        return code;
    }

    public VultureUserData getMessage() {
        return message;
    }

    @Override
    public boolean success() {
        return code == Config.CODE_SUCCEED;
    }
}
