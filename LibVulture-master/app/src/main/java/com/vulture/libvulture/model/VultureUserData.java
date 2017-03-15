package com.vulture.libvulture.model;

import com.vulture.libvulture.ApiClient;
import com.vulture.libvulture.VultureContract;

import java.io.IOException;

/**
 * Created by han on 2016/11/28.
 */

public class VultureUserData implements VultureContract.UserData{
    private String username;
    private String password;
    private long transfer;
    private long transfer_limit;
    private String inviter;

    public VultureUserData(String username,String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public long getTransfer() {
        return transfer;
    }

    @Override
    public long getTransferLimit() {
        return transfer_limit;
    }

    @Override
    public String getInviter() {
        return inviter;
    }

    @Override
    public boolean updateState() {
        try {
            VultureUserData data = ApiClient.getUserData(username,password);
            if(data != null){
                transfer = data.getTransfer();
                transfer_limit = data.transfer_limit;
                inviter = data.inviter;
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public VultureUserData setInviter(String inviter) {
        this.inviter = inviter;
        return this;
    }

    public VultureUserData setUsername(String username) {
        this.username = username;
        return this;
    }

    public VultureUserData setPassword(String password) {
        this.password = password;
        return this;
    }

    public VultureUserData setTransfer(long transfer) {
        this.transfer = transfer;
        return this;
    }

    public VultureUserData setTransfer_limit(long transfer_limit) {
        this.transfer_limit = transfer_limit;
        return this;
    }
}
