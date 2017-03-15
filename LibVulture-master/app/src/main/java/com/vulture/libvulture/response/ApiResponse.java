package com.vulture.libvulture.response;

/**
 * Created by SuiBian on 2017/1/18.
 */

public interface ApiResponse {

    public int getCode();

    public Object getMessage();

    public boolean success();
}
