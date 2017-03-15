package com.vulture.libvulture.model;

import android.content.Context;

import com.vulture.libvulture.ApiClient;
import com.vulture.libvulture.VultureContract;

import java.io.IOException;

/**
 * Created by han on 2016/12/6.
 */

public class VultureUser implements VultureContract.User {

    private String username;
    private String password;

    private VultureUserData mData;
    private VultureContract.Connection mConnection;

    private long mLastUpdateTime;

    public VultureUser(String username, String password) {
        this.username = username;
        this.password = password;
        mConnection = new VultureConnection(this);
        mData = new VultureUserData(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // call it after update or it won't promise the reliability
    // unit in Byte
    @Override
    public long getRemainTransfer() {
        return mData.getTransferLimit() - mData.getTransfer();
    }

    @Override
    public boolean updateState() {
        boolean result = mData.updateState() && mConnection.updateState();
        if (result) {
            mLastUpdateTime = System.currentTimeMillis();
        }
        return result;
    }

    @Override
    public long lastUpdateTime() {
        return mLastUpdateTime;
    }

    @Override
    public VultureContract.Connection getConnection() {
        return mConnection;
    }

    @Override
    public void startConnect(Context context) {
        mConnection.start(context);
    }


    @Override
    public VultureContract.UserData getUserData() {
        return mData;
    }

    @Override
    public void stopConnect() {
        mConnection.stop();
    }

    @Override
    public boolean prepareNewConnection() {
        return mConnection.updateState();
    }

    @Override
    public boolean prepareNewConnection(String ip) {
//        try {
//            mConnection = ApiClient.getSpecifyConnection(username,password,ip);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return mConnection != null;
        mConnection = new VultureConnection(this);
        mConnection.acquireSpecConnection(ip);
        return true;
    }
}
