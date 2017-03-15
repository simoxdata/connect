package com.vulture.libvulture.response;

import com.vulture.libvulture.Config;

/**
 * Created by han on 2016/11/28.
 */

public class DelConnectionResponse implements ApiResponse {
    private int code;
    private String message;

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public Object getMessage() {
        return null;
    }

    @Override
    public boolean success() {
        return code == Config.CODE_SUCCEED;
    }
}
