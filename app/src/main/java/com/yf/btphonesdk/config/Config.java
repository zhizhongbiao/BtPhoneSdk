package com.yf.btphonesdk.config;

/**
 * FileName :  Config
 * Author   :  zhizhongbiao
 * Date     :  2018/10/8
 * Describe :
 */

public interface Config {

    /**
     * 各个模块的Key值
     */
    interface ModuleKey {

        /**
         * 拨号键盘
         */
        int KEY_KEY_BOARD = 0;
        /**
         * 通话记录
         */
        @Deprecated
        int KEY_RECORD = 1;
        /**
         * 联系人
         */
        int KEY_CONTACTS = 2;
        /**
         * 常用联系人
         */
        int KEY_OFTEN = 3;
        /**
         * 蓝牙设置
         */
        int KEY_SETTING = 4;


        /**
         * 全部通话记录
         */
        int KEY_RECORD_ALL = 666;
        /**
         * 未接记录
         */
        int KEY_RECORD_MISSED = 777;
        /**
         * 已拨记录
         */
        int KEY_RECORD_DIALED = 888;
        /**
         * 已接记录
         */
        int KEY_RECORD_RECEIVED = 999;


    }

    /**
     * Action清单
     */
    interface Action {
        /**
         * 远程服务的Action
         */
        String ACTION_SERVICE = "com.yf.bluetooth.action.REMOTE_BIND";
    }


    /**
     * Exception清单
     */
    interface Exception {
        /**
         * 远程服务绑定错误
         */
        String REMOTE_SERVICE_BIND_FAILED = "com.yf.bluetooth.exception.REMOTE_BIND_FAILED";
    }


    /**
     * CallbackState清单
     */
    interface CallbackState {
        /**
         * 没有重拨号码
         */
        int NO_REDIAL_NUM = 99;
    }
}
