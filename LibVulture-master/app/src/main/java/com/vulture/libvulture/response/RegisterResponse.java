package com.vulture.libvulture.response;

import com.vulture.libvulture.Config;

/**
 * Created by han on 2016/11/28.
 */

public class RegisterResponse implements ApiResponse {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean success() {
        return code == Config.CODE_SUCCEED;
    }
}
