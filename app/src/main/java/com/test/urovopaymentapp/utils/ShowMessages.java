package com.test.urovopaymentapp.utils;

import android.content.Context;

import com.test.urovopaymentapp.R;

public class ShowMessages {

    public static String checkNetwork = "Checking Network";
    public static String sendGlabk = "Sending Data";
    public static String receviGlabk = "Receiving Data";
    public static String is_trading = "Trading is under way. Please wait a moment.";

    public static void setlanuageData(Context mContext) {

        checkNetwork = mContext.getString(R.string.checking_network);
        sendGlabk = mContext.getString(R.string.sending_data);
        receviGlabk = mContext.getString(R.string.being_returned);
    }
}
