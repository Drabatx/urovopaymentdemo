package com.test.urovopaymentapp.domain.repository;

import com.test.urovopaymentapp.data.model.StIso8583;

public interface PosEveCallBack {
	void onEventComplete(StIso8583 stIOS8583);
}