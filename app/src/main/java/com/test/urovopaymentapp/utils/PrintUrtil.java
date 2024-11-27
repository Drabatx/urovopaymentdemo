package com.test.urovopaymentapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.test.urovopaymentapp.R;
import com.test.urovopaymentapp.data.model.models.StIso8583;
import com.test.urovopaymentapp.data.model.pos2.constants.PosTransType;
import com.lib.log.Log;
import com.urovo.sdk.utils.DateUtil;

import java.io.OutputStream;
import java.util.Date;

public class PrintUrtil {
	public int prn_PagePrint(Context context, OutputStream outputStream)
			throws Exception {

		int nWidth, nHeight; // 打印内容的宽度、高度
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.bill);
		nWidth = bitmap.getWidth(); // g_nPageWidth;
		nHeight = bitmap.getHeight();// g_nPageHeight;
		byte[] pBuffer = new byte[nWidth * 3 + 7];
		pBuffer[0] = 0x1B;
		pBuffer[1] = 0x33;
		pBuffer[2] = 0x00;
		outputStream.write(pBuffer);
		// SendToPrinter(g_hPrinter, pBuffer, 3);
		pBuffer[0] = 0x1B;
		pBuffer[1] = 0x2A;
		pBuffer[2] = 0x21;
		pBuffer[3] = (byte) (nWidth & 0xFF);
		pBuffer[4] = (byte) ((nWidth & 0xFF00) >> 8);
		pBuffer[5 + nWidth * 3] = 0x0D;
		pBuffer[6 + nWidth * 3] = 0x0A;
		int nRowSize = bitmap.getRowBytes();
		for (int bar = 0; bar < nHeight / 24; ++bar) {
			int pix;
			for (int column = 0; column < nWidth; ++column) {
				// c = 7 - column % 8;
				pBuffer[5 + column * 3] = pBuffer[5 + column * 3 + 1] = pBuffer[5 + column * 3 + 2] = 0;
				for (int i = 0; i < 24; ++i) {
					pix = bitmap.getPixel(column, bar * 24 + i);
					pBuffer[5 + column * 3 + i / 8] |= ((pix == -1) ? 0 : 1) << (7 - i % 8);
				}
			}
			outputStream.write(pBuffer);
			outputStream.flush();
			Thread.sleep(300);
		}

		return 0;
	}

	public static String getDate(StIso8583 transdbModel) {

		try {
			String date = transdbModel.getField13();
			String time = transdbModel.getField12();
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer
					.append(DateUtil.formatDateByFormat(new Date(), "yyyy"));
			stringBuffer.append("/");
			stringBuffer.append(date.substring(0, 2));
			stringBuffer.append("/");
			stringBuffer.append(date.substring(2, 4));
			stringBuffer.append("  ");
			stringBuffer.append(time.substring(0, 2));
			stringBuffer.append(":");
			stringBuffer.append(time.substring(2, 4));
			stringBuffer.append(":");
			stringBuffer.append(time.substring(4, 6));
			return stringBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			Log.printLog("PrinntUtil" + e);
			return "";
		}

	}

	//
	public static String getPan(String string) {
		if(TextUtils.isEmpty(string)){
			return "";
		}
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(string.substring(0, 6));
		String String2 = string.substring(6, string.length() - 4);
		for (int i = 0; i < String2.length(); i++) {
			stringBuffer.append("*");
		}
		stringBuffer.append(string.substring(string.length() - 4,
				string.length()));
		return stringBuffer.toString();
	}

	// public static String getPan2(String string) {
	// StringBuffer stringBuffer = new StringBuffer();
	// for (int i = 0; i < string.length(); i++) {
	// stringBuffer.append(stringi);
	// }
	// return stringBuffer.toString();
	// }
	public static String getIssUser(String strign) {

		try {
			return strign.substring(17, strign.length());
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public static String getoldTranceNo(String strign) {

		try {
			return strign.substring(9, 15);
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	// 1标示在打印小票时显示的文字
	public static String getSaletype(int type, int flag) {
		String string = "";
		switch (type) {
		case PosTransType.POS_SALE:
			if (flag == 1)
				string = "消费/SALE";
			if (flag == 2)
				string = "消费/SALE";
			else
				string = "消费";
			break;
		case PosTransType.EC_QUICK_PAY:
		case PosTransType.EC_QUICK_UPLOAD:
			if (flag == 1)
				string = "电子现金消费/EC SALE";
			if (flag == 2)
				string = "电子现金消费/EC SALE";
			else {
				string = "电子现金消费";
			}
			break;
		case PosTransType.POS_SALE_VOID:
			if (flag == 1)
				string = "消费撤销/VOID";
			if (flag == 2)
				string = "消费撤销/VOID";
			else
				string = "消费撤销";
			break;
		case PosTransType.POS_PREAUTH:// 预授权
			if (flag == 1)
				string = "预授权/AUTH";
			if (flag == 2)
				string = "预授权/AUTH";
			else
				string = "预授权";
			break;
		case PosTransType.POS_PREAUTH_VOID: // 预授权撤销
		case PosTransType.POS_AUTH_VOID_QUERY:
			if (flag == 1)
				string = "预授权撤销/CANCEL";
			if (flag == 2)
				string = "预授权撤销/CANCEL";
			else
				string = "预授权撤销";
			break;
		case PosTransType.POS_AUTH_CM: // 预授权完成
			if (flag == 1)
				string = "预授权完成\nAUTH COMPLETE";
			if (flag == 2)
				string = "预授权完成/AUTH COMPLETE";
			else
				string = "预授权完成";

			break;
		case PosTransType.POS_AUTH_VOID: // 预授权完成撤销
			if (flag == 1)
				string = "完成撤销\nCOMPLETE VOID";
			if (flag == 2)
				string = "完成撤销/COMPLETE VOID";
			else
				string = "完成撤销";
			break;
		case PosTransType.POS_AUTH_CM_T: // 预授权完成撤销
			if (flag == 1)
				string = "完成通知";
			if (flag == 2)
				string = "完成通知";
			else
				string = "完成通知";
			break;
		case PosTransType.POS_REFUND:
			if (flag == 1)
				string = "退货/REFUND";
			if (flag == 1)
				string = "退货/REFUND";
			else
				string = "退货";
			break;
		case PosTransType.EC_QUICK_RETURN:
			if (flag == 1)
				string = "电子现金退货/EC REFUND";
			if (flag == 1)
				string = "电子现金退货/EC REFUND";
			else
				string = "电子现金退货";
			break;
		case PosTransType.INSTALLMENT_SALE:
			if (flag == 1)
				string = "分期消费/INSTALLMENT";
			if (flag == 2)
				string = "分期消费/INSTALLMENT";
			else
				string = "分期消费";
			break;
		case PosTransType.POS_AUTH_CM_HAND:
		case PosTransType.POS_AUTH_CM_QUERY:
			if (flag == 1)
				string = "预授权完成/AUTH COMPLETE";
			if (flag == 2)
				string = "预授权完成/AUTH COMPLETE";
			else
				string = "预授权完成";
			break;
		case PosTransType.POS_AUTH_VOID_HAND:
			if (flag == 1)
				string = "完成撤销/COMPLETE VOID";
			if (flag == 2)
				string = "完成撤销/COMPLETE VOID";
			else
				string = "完成撤销";

			break;
		case PosTransType.POS_PREAUTH_VOID_HAND:
			if (flag == 1)
				string = "预授权撤销/CANCEL";
			if (flag == 1)
				string = "预授权撤销/CANCEL";
			else
				string = "预授权撤销";
			break;

		}
		return string;
	}



}
