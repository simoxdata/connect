package com.vulture.libvulture.model;

/**
 * Created by han on 2017/2/3.
 */

public class VultureServer {

    private String ip;
    private int port;
    private String tag;
    private String tag_cn;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag_cn() {
        return tag_cn;
    }

    public void setTag_cn(String tag_cn) {
        this.tag_cn = tag_cn;
    }
}
