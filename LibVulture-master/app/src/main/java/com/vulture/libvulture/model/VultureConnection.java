package com.vulture.libvulture.model;

import android.content.Context;

import com.vm.shadowsocks.wrapper.SSService;
import com.vulture.libvulture.ApiClient;
import com.vulture.libvulture.VultureContract;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by han on 2016/11/28.
 */

public class VultureConnection implements VultureContract.Connection{

    // serialized from json
    private String ip;
    private int port;
    private String password;

    private long establish_time;
    private int TTL;

    private long transfer;

    // in ms
    private int REFRESH_RATE = 60 * 1000;

    private long mConnectionEstablishTime;
    // transfer after connect
    private long mCurrentTransfer;

    private long mLastUpdateTime;

    private Timer mTimer;

    private VultureContract.User mUser;

    private long startTransfer;

    public VultureConnection(VultureContract.User user){
        mUser = user;
    }

    public VultureContract.User getUser() {
        return mUser;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public long getEstablish_time() {
        return establish_time;
    }

    public int getTTL() {
        return TTL;
    }

    // it does't make sense since the shadowsocks port won't close after connection close
    public long getTransfer() {
        return transfer;
    }


    @Override
    public void start(Context context) {

        startTransfer = transfer;
        mCurrentTransfer = 0;
        mConnectionEstablishTime = System.currentTimeMillis();

        SSService.connect(context,ip,"" + port,password);

        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateState();
            }
        },0,REFRESH_RATE);

    }

    @Override
    public void stop() {
        SSService.stop();
        mConnectionEstablishTime = 0;
        mTimer.cancel();
    }


    @Override
    public boolean isConnecting() {
        return SSService.isRunning();
    }

    @Override
    public long getStartTime() {
        return mConnectionEstablishTime;
    }

    // it's realtime
    @Override
    public long getActiveTime() {
        return System.currentTimeMillis() - mConnectionEstablishTime;
    }

    @Override
    public long getCostTransfer() {
        return mCurrentTransfer;
    }

    @Override
    public boolean updateState(){
        try {
            VultureConnection c = ApiClient.getConnection(mUser.getUsername(),mUser.getPassword());
            if(c == null){
                return false;
            }

            ip = c.getIp();
            port = c.getPort();
            password = c.getPassword();
            establish_time = c.getEstablish_time();
            TTL = c.getTTL();
            transfer = c.getTransfer();
            mCurrentTransfer = transfer - startTransfer;

            mLastUpdateTime = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean acquireSpecConnection(String ip) {
        try {
            VultureConnection c = ApiClient.getSpecifyConnection(mUser.getUsername(),mUser.getPassword(),ip);
            if(c == null){
                return false;
            }

            this.ip = c.getIp();
            port = c.getPort();
            password = c.getPassword();
            establish_time = c.getEstablish_time();
            TTL = c.getTTL();
            transfer = c.getTransfer();
            mCurrentTransfer = transfer - startTransfer;

            mLastUpdateTime = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
