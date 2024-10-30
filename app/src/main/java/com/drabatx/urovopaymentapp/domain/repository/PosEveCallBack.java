package com.drabatx.urovopaymentapp.domain.repository;

import com.drabatx.urovopaymentapp.data.model.StIso8583;

public interface PosEveCallBack {
	void onEventComplete(StIso8583 stIOS8583);
}