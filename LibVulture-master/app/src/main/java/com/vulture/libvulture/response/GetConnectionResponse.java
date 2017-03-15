package com.vulture.libvulture.response;


import com.vulture.libvulture.Config;
import com.vulture.libvulture.model.VultureConnection;

/**
 * Created by han on 2016/11/28.
 */

public class GetConnectionResponse implements ApiResponse {
    private int code;
    private VultureConnection message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public VultureConnection getMessage() {
        return message;
    }

    @Override
    public boolean success() {
        return code == Config.CODE_SUCCEED;
    }
}
