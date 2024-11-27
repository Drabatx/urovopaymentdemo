package com.test.urovopaymentapp.data.repository;

import android.content.Context;

import com.test.urovopaymentapp.data.model.models.StIso8583;
import com.test.urovopaymentapp.data.model.pos2.models.PosInputDatas;

public abstract class BaseHelp {

	public abstract StIso8583 getSendPackage(PosInputDatas posInStr, Context context) throws Exception;

}
