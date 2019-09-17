package com.yf.btphonesdk.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.yf.btphonesdk.BtPhoneCallback;
import com.yf.btphonesdk.BtPhoneController;
import com.yf.btphonesdk.config.Config;
import com.yf.btphonesdk.util.LogUtils;

/**
 * FileName :  BtPhoneManager
 * Author   :  zhizhongbiao
 * Date     :  2018/10/8
 * Describe :
 */

public class BtPhoneManager implements ServiceConnection, IBinder.DeathRecipient {

    private static BtPhoneManager instance;
    private Context context;
    private BtPhoneController btPhoneController;

    /**
     * 构造函数
     */
    private BtPhoneManager() {}

    /**
     * 初始化函数必须调用，并需要放在应用程序启动初始化调用；
     *
     * @param context 上下文
     */
    public void init(Context context) {
        this.context = context;
        bindService(context);
        LogUtils.i("init  context=" + context);
    }

    /**
     * 绑定服务
     *
     * @param context 上下文
     */
    private void bindService(Context context) {
        if (context == null) {
            LogUtils.e("bindService  context=null");
            return;
        }
        Intent intent = new Intent(Config.Action.ACTION_SERVICE);
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    /**
     * 获取本类实例，首次请在Application中调用
     *
     * @return
     */
    public static BtPhoneManager getInstance() {
        if (instance == null) {
            synchronized (BtPhoneManager.class) {
                if (instance == null) {
                    instance = new BtPhoneManager();
                }
            }
        }
        return instance;
    }


    /**
     * 绑定服务成功回调
     *
     * @param name
     * @param service
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        btPhoneController = BtPhoneController.Stub.asInterface(service);
        LogUtils.i("onServiceConnected  绑定成功");
        try {
            //监听远程服务连接异常断开死亡
            btPhoneController.asBinder().linkToDeath(this, 0);
        } catch (RemoteException e) {
            LogUtils.e("远程出错  exception=" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 绑定异常断开回调
     *
     * @param name
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {

    }


    /**
     * 绑定异常死亡断开回调
     */
    @Override
    public void binderDied() {
        btPhoneController.asBinder().unlinkToDeath(this, 0);
        btPhoneController = null;
        instance = null;
        instance = getInstance();
        init(context);
    }


    /**
     * 跳转到对应的界面
     *
     * @param key 对应界面的key{@link Config.ModuleKey}值
     * @throws RemoteException
     */
    public boolean goToModule(int key) throws RemoteException {
        if (btPhoneController == null) {
            bindService(context);
            return false;
        }

        btPhoneController.goToModule(key);
        return true;
    }


    /**
     * 跳转蓝牙电话对应的Activity
     *
     * @throws RemoteException
     */
    public boolean goToBtActivity() throws RemoteException {
        if (btPhoneController == null) {
            bindService(context);
            return false;
        }

        btPhoneController.goToBtActivity();
        return true;
    }

    public boolean exitBtUI() throws RemoteException {
        if (btPhoneController == null) {
            bindService(context);
            return false;
        }

        btPhoneController.exitBtUI();
        return true;
    }


    /**
     * 重播电话
     *
     * @return
     * @throws RemoteException
     */
    public boolean redial() throws RemoteException {
        if (btPhoneController == null) {
            bindService(context);
            return false;
        }

        btPhoneController.redial();
        return true;
    }


    /**
     * 注册监听器
     *
     * @param c
     * @return
     * @throws RemoteException
     */
    public boolean registerBtPhoneCallback(BtPhoneCallback c) throws RemoteException {
        if (btPhoneController == null) {
            bindService(context);
            return false;
        }

        btPhoneController.registerBtPhoneCallback(c);
        return true;
    }

    /**
     * 反注册监听器
     *
     * @param c
     * @return
     * @throws RemoteException
     */
    public boolean unregisterBtPhoneCallback(BtPhoneCallback c) throws RemoteException {
        if (btPhoneController == null) {
            bindService(context);
            return false;
        }

        btPhoneController.unregisterBtPhoneCallback(c);
        return true;
    }

}
