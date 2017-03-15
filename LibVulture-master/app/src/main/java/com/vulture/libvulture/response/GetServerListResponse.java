package com.vulture.libvulture.response;

import com.vulture.libvulture.Config;
import com.vulture.libvulture.model.VultureServer;

/**
 * Created by han on 2017/2/3.
 */

public class GetServerListResponse implements ApiResponse {
    private int code;
    private VultureServer[] message;
    @Override
    public int getCode() {
        return code;
    }

    @Override
    public Object getMessage() {
        return message;
    }

    @Override
    public boolean success() {
        return code == Config.CODE_SUCCEED;
    }
}
