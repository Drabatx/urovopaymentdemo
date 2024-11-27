package com.test.urovopaymentapp.data.repository;

import android.content.Context;
import android.text.TextUtils;

import com.lib.card.constant.CardTypeConstant;
import com.test.urovopaymentapp.data.local.preferences.MerchantParams;
import com.test.urovopaymentapp.data.model.models.StIso8583;
import com.test.urovopaymentapp.data.model.pos2.models.PosInputDatas;
import com.test.urovopaymentapp.utils.FunsUtils;

import java.util.Objects;

public class SaleHelp extends BaseHelp {

	@Override
	public StIso8583 getSendPackage(PosInputDatas posInStr, Context context) throws Exception {
		StIso8583 stIOS8583 = new StIso8583();
		stIOS8583.setSzMsgCode("0200");
		stIOS8583.setField2(posInStr.getPan());
		stIOS8583.setField3("000000");
		stIOS8583.setField4(FunsUtils.StrAmtTOStr(posInStr.getAmt()));
		stIOS8583.setField11(MerchantParams.INSTANCE.getTraceNum());
		if (!TextUtils.isEmpty(posInStr.getSzExpDate())) {
			stIOS8583.setField14(posInStr.getSzExpDate());
		}
//		stIOS8583.setField22(BaseHelpUtils.getFile22(posInStr));
//		stIOS8583.setField23(BaseHelpUtils.getField23(posInStr));
		stIOS8583.setField25("00");
		if (!TextUtils.isEmpty(posInStr.getSzPINData())) {
			stIOS8583.setField26("12");
		}
		stIOS8583.setField41(Objects.requireNonNull(MerchantParams.INSTANCE.getTerminalNum()));// 41
		stIOS8583.setField42(Objects.requireNonNull(MerchantParams.INSTANCE.getMerchantNum())); // 42 Card recipient identification code (merchant code);
		stIOS8583.setField49("156");
		stIOS8583.setField52(posInStr.getSzPINData());
//		stIOS8583.setField53(BaseHelpUtils.getfile53(posInStr));
		if (posInStr.getSwipedMode() == CardTypeConstant.RFID || posInStr.getSwipedMode() == CardTypeConstant.IC) {
			stIOS8583.setField55(Objects.requireNonNull(posInStr.getFile55()));
		}
		stIOS8583.setField60("22" + MerchantParams.INSTANCE.getBatchNum() + "000601");// 60
		stIOS8583.setField63(FunsUtils.FormatWithZero(MerchantParams.INSTANCE.getUserName(), "000"));//"001";
		stIOS8583.setField64("0000000000000000"); // 64 mac
		return stIOS8583;
	}
}
