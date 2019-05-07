// BtPhoneController.aidl
package com.yf.btphonesdk;

import com.yf.btphonesdk.BtPhoneCallback;
// Declare any non-default types here with import statements

interface BtPhoneController {

    void goToModule(int key);

    void goToBtActivity();

    void exitBtUI();

     void redial();

     void registerBtPhoneCallback(BtPhoneCallback c);

     void unregisterBtPhoneCallback(BtPhoneCallback c);
}
