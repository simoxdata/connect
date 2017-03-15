package com.vulture.libvulture.response;

import com.vulture.libvulture.Config;

/**
 * Created by SuiBian on 2017/1/19.
 */

public class InviteResponse implements ApiResponse {
    private int code;
    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean success() {
        return code == Config.CODE_SUCCEED;
    }
}
