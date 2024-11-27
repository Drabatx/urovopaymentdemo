package com.test.urovopaymentapp.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

public class FunsUtils {

	public static boolean BLFirstRun = false;

	public static String pinString = "";

	private static final String STR_FORMAT = "000000";
	public static PackageManager pm;
	public static Context context;

	public static String FilePath = "";
	public static String debuglogfilename = "";

	public static byte[] getxor(byte[] a, byte[] b, int len) {
		int result = 0;
		for (int i = 0; i < len; i++) {
			a[i] ^= b[i];
		}
		return a;
	}

	public static byte getxor(byte[] a, int index, int len) {
		int result = 0;

		int data2 = 0;
		int data1 = 0;

		data1 = 0x00ff & a[index];
		for (int i = index + 1; i < index + len; i++) {
			data2 = 0x00ff & a[i];
			data1 ^= data2;
		}
		return (byte) (0x00ff & data1);
	}

	public static void SetBytesValue(byte[] B, int len, byte v) {
		int i = 0;
		for (i = 0; i < len; i++) {
			Array.setByte(B, i, v);
		}
	}

	public static void SleepMS(int MS) {
		try {
			Thread.sleep(MS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void do_xor_urovo(byte[] src1, byte[] src2, int num) {
		int i;

		int data2 = 0;
		int data1 = 0;

		for (i = 0; i < num; i++) {
			data1 = 0x00ff & src1[i];
			data2 = 0x00ff & src2[i];
			data1 ^= data2;
			src1[i] = (byte) (data1 & 0x00ff);
		}

	}

	public static String haoAddOne(String liuShuiHao) {
		Integer intHao = Integer.parseInt(liuShuiHao);
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		return df.format(intHao);
	}

	// 转成以FMT格式字符
	public static String FormatWithZero(String str, String Fmt) {
		try {
			Integer itr = Integer.parseInt(str);
			DecimalFormat df = new DecimalFormat(Fmt);
			return df.format(itr);
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public static String FormatWithZero(String str, int length) {
		try {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < length - str.length(); i++) {
				sb.append("0");
			}
			sb.append(str);
			return sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	// 转成以FMT格式字符
	public static String FormatWithvalue(String str, String value) {
		try {
			int len = value.length();
			if (str.length() >= len) {
				return str;
			} else {
				String temp = "";
				temp = str;
				temp += value.substring(str.length(), value.length());
				return temp;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}

	// 转成以FMT格式字符
	public static String FormatWithvalueR(String str, String value) {
		try {
			int len = value.length();
			if (str.length() >= len)
				return str;
			else {
				String temp = "";
				temp = str;
				temp += value.substring(str.length(), value.length());
				return temp;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}

	// 转成以FMT格式字符
	public static String FormatvalueByLeft(String str, String value) {
		try {
			int len = value.length();
			if (str.length() >= len) {
				return str;
			} else {
				String temp = "";

				temp = value.substring(0, value.length() - str.length());
				temp += str;
				return temp;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}

	public static String GetLocalDatetime() {

		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sDateFormat.format(new java.util.Date());
		// 当前日期
		String status = "";
		status = date.substring(0, 4);
		status += "/";
		status += date.substring(5, 5 + 2);
		status += "/";
		status += date.substring(8, 8 + 2);
		status += " ";

		// 当前时间
		status += date.substring(11, 11 + 2);
		status += ":";
		status += date.substring(14, 14 + 2);
		status += ":";
		status += date.substring(17, 17 + 2);

		return status;
	}

	public static byte abcd_to_asc(byte ucBcd) {
		byte ucAsc;

		ucBcd &= 0x0f;
		if (ucBcd <= 9)
			ucAsc = (byte) (ucBcd + (byte) ('0'));
		else
			ucAsc = (byte) (ucBcd + (byte) ('A') - (byte) 10);
		return (ucAsc);
	}

	/**
	 * 00000000001 转 0.01
	 * @param amt
	 * @return
	 */
	public static String IntToAmt(int amt) {

		String StrTrace = "" + amt;
		String str1;
		String str2;
		if (amt < 0) {
			StrTrace = StrTrace.replace("-", "");
		}
		if (StrTrace.length() == 2) {
			StrTrace = "0." + StrTrace;
		} else if (StrTrace.length() == 1) {
			StrTrace = "0.0" + StrTrace;
		} else {
			str1 = StrTrace.substring(StrTrace.length() - 2, StrTrace.length());
			str2 = StrTrace.substring(0, StrTrace.length() - 2);
			StrTrace = str2 + "." + str1;
		}
		if (amt < 0) {
			StrTrace = "- " + StrTrace;
		}
		return StrTrace;
	}

	/**
	 * 00000000001 转 0.01
	 * @param amt
	 * @return
	 */
	public static String IntToAmt(Long amt) {

		String StrTrace = "" + amt;
		String str1;
		String str2;
		if (StrTrace.length() == 2)
			StrTrace = "0." + StrTrace;
		else if (StrTrace.length() == 1)
			StrTrace = "0.0" + StrTrace;
		else {
			str1 = StrTrace.substring(StrTrace.length() - 2, StrTrace.length());
			str2 = StrTrace.substring(0, StrTrace.length() - 2);
			StrTrace = str2 + "." + str1;
		}
		return StrTrace;
	}

	public static String StrAmtTOStr(String tranAmt) {
		Log.e("amt1", tranAmt);
		String szAmt = new DecimalFormat("0.00").format(Double.parseDouble(tranAmt));
		szAmt = szAmt.replace(".", "");
		Log.e("amt2", szAmt);
		szAmt = FormatvalueByLeft(szAmt, "000000000000");
		return szAmt;
	}

	public static void BcdToAsc(byte[] sAscBuf, byte[] sBcdBuf, int iAscLen) {
		int i, j;

		j = 0;
		for (i = 0; i < iAscLen / 2; i++) {
			sAscBuf[j] = (byte) ((sBcdBuf[i] & 0xf0) >> 4);
			sAscBuf[j] = abcd_to_asc(sAscBuf[j]);
			j++;
			sAscBuf[j] = (byte) (sBcdBuf[i] & 0x0f);
			sAscBuf[j] = abcd_to_asc(sAscBuf[j]);
			j++;
		}
		if ((iAscLen % 2) != 0) {
			sAscBuf[j] = (byte) ((sBcdBuf[i] & 0xf0) >> 4);
			sAscBuf[j] = abcd_to_asc(sAscBuf[j]);
		}
	}

	/****************************************************************************
	 ****************************************************************************/
	public static byte aasc_to_bcd(byte ucAsc) {
		byte ucBcd;

		if ((ucAsc >= '0') && (ucAsc <= '9'))
			ucBcd = (byte) (ucAsc - '0');
		else if ((ucAsc >= 'A') && (ucAsc <= 'F'))
			ucBcd = (byte) (ucAsc - 'A' + 10);
		else if ((ucAsc >= 'a') && (ucAsc <= 'f'))
			ucBcd = (byte) (ucAsc - 'a' + 10);
		else if ((ucAsc > 0x39) && (ucAsc <= 0x3f))
			ucBcd = (byte) (ucAsc - '0');
		else
			ucBcd = 0x0f;

		return ucBcd;
	}

	/****************************************************************************
	 ****************************************************************************/
	public static void AscToBcd(byte[] sBcdBuf, byte[] sAscBuf, int iAscLen) {
		int i, j;

		j = 0;

		for (i = 0; i < (iAscLen + 1) / 2; i++) {
			sBcdBuf[i] = (byte) (aasc_to_bcd(sAscBuf[j++]) << 4);
			if (j >= iAscLen) {
				sBcdBuf[i] |= 0x00;
			} else {
				sBcdBuf[i] |= aasc_to_bcd(sAscBuf[j++]);
			}
		}
	}

	public static void BcdToAsc0(byte[] sAscBuf, byte[] sBcdBuf, int iAscLen) {
		BcdToAsc(sAscBuf, sBcdBuf, iAscLen);
		sAscBuf[iAscLen] = 0;
	}

	public static void ATBByIndex(byte[] sBcdBuf, int sBcdIndex, byte[] sAscBuf, int sAscIndex, int iAscLen) {
		byte[] Asctemp = new byte[iAscLen];
		byte[] Bcdtemp = new byte[(iAscLen + 1) / 2];

		System.arraycopy(sAscBuf, sAscIndex, Asctemp, 0, iAscLen);
		AscToBcd(Bcdtemp, Asctemp, iAscLen);
		System.arraycopy(Bcdtemp, 0, sBcdBuf, sBcdIndex, (iAscLen + 1) / 2);
	}

	public static void BTAByIndex(byte[] sBcdBuf, int sBcdIndex, byte[] sAscBuf, int sAscIndex, int iAscLen) {
		byte[] Asctemp = new byte[iAscLen];
		byte[] Bcdtemp = new byte[(iAscLen + 1) / 2];

		System.arraycopy(sBcdBuf, sBcdIndex, Bcdtemp, 0, (iAscLen + 1) / 2);
		BcdToAsc(Asctemp, Bcdtemp, iAscLen);
		System.arraycopy(Asctemp, 0, sAscBuf, sAscIndex, iAscLen);

	}

	public static String SetStrTlv(String tag, String value) {
		long l = value.length();
		String strlen = String.format("%04X", l);

		String tempstrvalue = "";
		if (tag == "0030" || tag == "0031" || tag == "0000" || tag == "0001" || tag == "0002" || tag == "0003"
				|| tag == "0004" || tag == "0005" || tag == "0010" || tag == "0015" || tag == "0020" || tag == "0021"
				|| tag == "0022" || tag == "0023" || tag == "0001" || tag == "0045" || tag == "0046" || tag == "0047"
				|| tag == "0053" || tag == "0054" || tag == "0055" || tag == "0056") {
			byte[] srcbytes = value.getBytes();
			byte[] dstbytes = new byte[value.length() * 2];
			BcdToAsc(dstbytes, srcbytes, dstbytes.length);
			tempstrvalue = new String(dstbytes);

		}

		return tag + strlen + tempstrvalue;
	}

	/****************************************************************************
	 * 功能描述: 一个字节BCD码转换为int值 输入参数: ucBcd: 源BCD码 输出参数: 返 回 值: 目的int值
	 ****************************************************************************/
	public static byte bcd_to_byte(byte ucBcd) {
		byte temp, temp2;
		temp = ucBcd;
		temp >>= 4;
		temp &= 0x0f;
		temp *= 10;

		temp2 = ucBcd;
		temp2 &= 0x0f;

		return (byte) (temp + temp2);
		// return (((ucBcd >> 4) & 0x0f) * 10 + (ucBcd & 0x0f));
	}

	/****************************************************************************
	 * 功能描述: iBcdLen字节BCD码串转换为int值 输入参数: sBcdBuf: 源BCD串首地址 iBcdLen: 源BCD串字节数
	 * 输出参数: 返 回 值: 目的int值
	 ****************************************************************************/
	public static int BcdToInt(byte[] sBcdBuf, int iBcdLen) {
		int iValue = 0, i = 0;
		byte temp;

		if (iBcdLen <= 0)
			return 0;

		while (i < iBcdLen) {
			temp = sBcdBuf[i];
			iValue = iValue * 100 + bcd_to_byte(temp);
			i++;
		}

		return iValue;
	}

	//
	public static int comparabytes(byte[] temp1, byte[] temp2, int len) {
		for (int i = 0; i < len; i++) {
			if (temp1[i] != temp2[i]) {
				return -1;
			}
		}

		return 0;
	}

	public static int memcmp(byte[] temp1, byte[] temp2, int len) {
		for (int i = 0; i < len; i++) {
			if (temp1[i] != temp2[i]) {
				return -1;
			}
		}
		return 0;
	}

	// 转成字符
	public static String byte2hex(byte[] data) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			String temp = Integer.toHexString((data[i]) & 0xFF);
			for (int t = temp.length(); t < 2; t++) {
				sb.append("0");
			}
			sb.append(temp);
		}
		return sb.toString();
	}

	// 转成16进制
	public static byte[] StrToHexByte(String str) {
		if (str == null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
			}
			return buffer;
		}
	}

	public static String GetTLVStr(String SrcStr, String KeyWordStr) {
		int i = 0;
		int index = 0;
		int len = 0;
		int lenMax = SrcStr.length();
		String tempstr = "";
		String Strlen = "";
		byte Blen[] = new byte[2];

		while (index < lenMax - 6) {
			tempstr = SrcStr.substring(index, 4);
			if (tempstr == KeyWordStr) {
				Strlen = SrcStr.substring(index + 4, 4);
				Blen[0] = Blen[1] = 0;
				Blen = FunsUtils.StrToHexByte(Strlen);
				len = Blen[0];
				len <<= 8;
				len |= Blen[1];
				index += 8;
				tempstr = SrcStr.substring(index, len);
				return tempstr;
			} else {
				Strlen = SrcStr.substring(index + 4, 4);
				Blen[0] = Blen[1] = 0;
				Blen = FunsUtils.StrToHexByte(Strlen);
				len = Blen[0];
				len <<= 8;
				len |= Blen[1];
				index += 8;
				index += len;
			}
		}
		return "";
	}

	public static void _vTwoOne(byte[] input, int in_len, byte[] output) {
		byte tmp;
		int i;

		for (i = 0; i < in_len; i += 2) {
			tmp = input[i];
			if (tmp > '9') {
				tmp = (byte) (tmp - ('A' - 0x0A));
			} else {
				tmp &= 0x0f;
			}

			tmp <<= 4;
			output[i / 2] = tmp;

			if (i >= in_len - 1) {
				break;
			}
			tmp = input[i + 1];
			if (tmp > '9')
				tmp = (byte) (tmp - ('A' - 0x0A));
			else
				tmp &= 0x0f;
			output[i / 2] += tmp;
		}

	}

	public static void Replace_D(byte[] track, int len) {
		for (int i = 0; i < len; i++) {
			if (track[i] == '=') {
				track[i] = 'D';
			}
		}

	}

	/****************************************************************************
	 * Function: 从磁道中取卡号 Param In: track2 2磁道数据 track3 3磁道数据 Param Out: szCardNo
	 * 卡号，带结束符 Return Code: E_ERR_SWIPE 磁道信息错； OK 成功
	 ****************************************************************************/
	public static byte GetCardFromTrack(byte[] szCardNo, byte[] track2, byte[] track3) {
		int i;
		/* POS端不存卡表，从2磁道开始到‘＝’ */
		if (ByteArrayLength(track2) != 0) {
			i = 0;
			while (track2[i] != '=') {
				if (i > 19)
					return 2;
				i++;
			}
			if (i < 13 /* || i>19 */)
				return 2;

			System.arraycopy(track2, 0, szCardNo, 0, i);
			szCardNo[i] = 0;
		} else if (ByteArrayLength(track3) != 0) {
			i = 0;
			while (track3[i] != '=') {
				if (i > 21)
					return 3;
				i++;
			}
			if (i < 15 /* || i > 21 */)
				return 3;
			System.arraycopy(track3, 2, szCardNo, 0, i - 2);
			szCardNo[i - 2] = 0;
		}

		return 0;
	}

	/**
	 * 把字节数组转换成16进制字符串
	 *
	 * @param bArray
	 * @return
	 */
	public static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/*
	 * public static int EncrtpTracks(byte[] track, byte[] OutEncrptTrack, int[]
	 * len,int wihchtrack) { int i = 0; String tempstr = ""; String strs = "";
	 *
	 * int tlen = track.length;
	 *
	 * String str = new String(track); str=str.replaceAll("=", "D");
	 *
	 * byte[] tempbytes = new byte[track.length + 2];
	 *
	 * if (tlen > 99) { str = "0" + tlen + str; tlen += 4; } else { if
	 * (wihchtrack==3) { str = "00"+tlen+ str; tlen += 4; } else { str = ""+tlen
	 * + str; tlen += 2; }
	 *
	 * }
	 *
	 * byte[] bytestr = str.getBytes(); Funs._vTwoOne(bytestr, bytestr.length,
	 * tempbytes);
	 *
	 *
	 * byte[] EncrptTrack = new byte[256]; byte[] inputs = new byte[8]; byte[]
	 * outputs = new byte[8];
	 *
	 * byte[] keys = new byte[16];
	 *
	 * Arrays.fill(keys, (byte)0);
	 *
	 * if ((tlen % 2) != 0) { tlen++; } int k = (tlen / 2) / 8; ;
	 *
	 * int j = (tlen / 2) % 8;
	 *
	 * Arrays.fill(EncrptTrack, (byte)0);
	 *
	 * int ret=0; byte[] reslen=new byte[1]; if (j == 0) { //可以进行加密了
	 *
	 * Arrays.fill(inputs, (byte)0); Arrays.fill(outputs, (byte)0); k =
	 * str.length() / 8; for (i = 0; i < k; i++) { Arrays.fill(inputs, (byte)0);
	 * Arrays.fill(outputs, (byte)0); System.arraycopy(tempbytes, i * 8, inputs,
	 * 0, 8);
	 *
	 * if(Maxq_Api.Maxq1850init()) {
	 * ret=Maxq_Api.Urovo_PciDes((byte)stPosParams.EncryptIndex, 8, inputs,
	 * outputs, (byte)1); } System.arraycopy(outputs, 0, EncrptTrack, i * 8, 8);
	 * } } else { byte[] NewTrack = new byte[tempbytes.length];
	 *
	 * Arrays.fill(NewTrack, (byte)0); System.arraycopy(tempbytes, 0, NewTrack,
	 * 0, tlen / 2 + 1);
	 *
	 * Arrays.fill(inputs, (byte)0); Arrays.fill(outputs, (byte)0); //k =
	 * NewTrack.Length / 8; k++; for (i = 0; i < k; i++) { Arrays.fill(inputs,
	 * (byte)0); Arrays.fill(outputs, (byte)0); System.arraycopy(NewTrack, i *
	 * 8, inputs, 0, 8);
	 *
	 * if(Maxq_Api.Maxq1850init()) {
	 * ret=Maxq_Api.Urovo_PciDes((byte)stPosParams.EncryptIndex, 8, inputs,
	 * outputs, (byte)1); if(ret!=0) {
	 * Tracelog("mMaxqManager.encryptData:"+ret+"\r\n"); } }
	 * System.arraycopy(outputs, 0, EncrptTrack, i * 8, 8); } }
	 *
	 *
	 * len[0] = i * 8; Arrays.fill(OutEncrptTrack, (byte)0);
	 * System.arraycopy(EncrptTrack,0,OutEncrptTrack,0,len[0]);
	 *
	 * //调试信息 if (GlobalDefine._DEBUG == true) { strs = "磁道加密数据："; for (j = 0; j
	 * < len[0]; j++) { tempstr = String.format("%02X", EncrptTrack[j]); strs +=
	 * tempstr; } strs += "\r\n\r\n"; Tracelog(strs); }
	 *
	 * return GlobalDefine._OK; }
	 */

	public static byte ByteArrayLength(byte[] Array) {
		int i = 0;
		while (Array[i] != 0x00) {
			i++;
		}

		return (byte) i;
	}

	/****************************************************************************
	 * 功能描述: 将以分为单位的asc串转为以元为单位的asc串表示金额，asc串以'\0'结束 输入参数: szAmount:
	 * （以分为单位）asc串首地址 输出参数: szAmount: 目的（以元为单位）asc串首地址 返 回 值: asc串长度
	 ****************************************************************************/
	public static byte LongToDec(byte[] szAmount) {
		byte[] tmp = new byte[16];
		byte len1;
		System.arraycopy("0.00".getBytes(), 0, tmp, 0, 4);
		tmp[4] = 0;
		len1 = ByteArrayLength(szAmount);
		if (len1 == 0) {
			System.arraycopy(tmp, 0, szAmount, 0, 4);
			szAmount[4] = 0;
			return (4);
		}
		if (len1 == 1) {
			tmp[3] = szAmount[0];
			System.arraycopy(tmp, 0, szAmount, 0, 4);
			szAmount[4] = 0;
			return (4);
		}
		if (len1 == 2) {
			tmp[2] = szAmount[0];
			tmp[3] = szAmount[1];
			System.arraycopy(tmp, 0, szAmount, 0, 4);
			szAmount[4] = 0;
			return (4);
		}

		System.arraycopy(szAmount, 0, tmp, 0, len1 - 2);
		tmp[len1 - 2] = '.';
		System.arraycopy(szAmount, len1 - 2, tmp, len1 - 1, 2);
		tmp[len1 + 1] = 0;
		System.arraycopy(tmp, 0, szAmount, 0, len1 + 1);
		szAmount[len1 + 1] = 0;
		return (byte) (len1 + 1);
	}

	/****************************************************************************
	 * 功能描述: 6bytes BCD 金额转换成可显示的金额 输入参数: bcdAmt BCD金额 6bytes 左补0 输出参数:
	 * amount_ptr 格式化的金额 返 回 值: 转换出的金额长度 example: bcdAmt:
	 * "\x00\x00\x02\x00\x00\x00" --->> "20000.00"
	 ****************************************************************************/
	public static byte ConvBcdAmount(byte[] bcdAmt, byte[] amount_ptr) {
		byte[] buffer = new byte[16];
		byte[] startbuff = new byte[16];
		byte amtLen;
		int i;

		BcdToAsc(buffer, bcdAmt, 12);
		buffer[12] = 0x00;
		for (i = 0; i < 12; i++) {
			if (buffer[i] != '0') {
				System.arraycopy(buffer, i, startbuff, 0, 12 - i);
				amtLen = LongToDec(startbuff);
				System.arraycopy(startbuff, 0, amount_ptr, 0, amtLen);
				return amtLen;
			}
		}

		System.arraycopy("0.00".getBytes(), 0, amount_ptr, 0, 4);
		return 4;
	}

	public static int byte2int(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000

		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00); // | 表示安位或
		return targets;
	}

	public static int atoi(byte[] res) {
		int iRet = (res[0] - 0x30) * 10 + (res[1] - 0x30);
		return iRet;
	}

	public static short byte2short(byte[] buf) {
		short s1 = 0;
		short s2 = 0;

		s1 = buf[0];
		s1 <<= 8;

		s2 = buf[1];

		return (short) (s1 + s2);
	}

	// //////////////////////////tag处理
	public static int GetTlvItem(String Tagstr, String buffstr, String outstr) {
		int index = 0;
		int tempTag = 0;
		int tag = Integer.parseInt(Tagstr, 16);
		byte[] Asc = buffstr.getBytes();
		int Alen = Asc.length;
		byte[] Bcd = new byte[Alen / 2];

		AscToBcd(Bcd, Asc, Alen);

		return 0;
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		try {
			for (int i = 0; i < len; i += 2) {
				data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
			}
		} catch (Exception e) {
			Log.d("debug", "Argument(s) for hexStringToByteArray(String s)" + "was not a hex string");
		}
		return data;
	}

	public static boolean isHexAnd16Byte(String hexString) {
		if (hexString.matches("[0-9A-Fa-f]+") == false) {
			// Error, not hex.
			return false;
		}
		return true;
	}

	public static String bytesToHexString(byte[] src, int len) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		if (len <= 0) {
			return "";
		}
		for (int i = 0; i < len; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 十六进制转十进制
	 *
	 * @param balance
	 * @return
	 */
	public static int hexToDec(String number) {
		return (int) Long.parseLong(number, 16);
	}

	/**
	 * 将十进制数字金额(单位：元)格式化成8位十六进制 0.01--->00000001
	 *
	 * @param amt
	 * @return
	 */
	public static String decToHex(String number) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		String s = decimalFormat.format(Double.parseDouble(number));
		String s1 = Integer.toHexString(Integer.parseInt(s.replace(".", "")));
		String s2 = FunsUtils.FormatvalueByLeft(s1, "00000000");
		return s2;
	}

	/**
	 * 逆序十六进制转十进制 E803-->逆序03E8-->1000
	 *
	 * @param hex
	 *            需要转换的十六进制数
	 * @return
	 */
	public static int hexToDec_LH(String hex) {
		int length = hex.length() / 2;
		StringBuilder sb = new StringBuilder();
		String[] s = new String[length];
		for (int i = 0; i < length; i++) {
			s[i] = hex.substring(i * 2, (i + 1) * 2);
		}
		Collections.reverse(Arrays.asList(s));
		for (int i = 0; i < length; i++) {
			sb.append(s[i]);
		}
		return (int) Long.parseLong(sb.toString(), 16);
	}

	/**
	 * 十进制转十六进制逆序输出 1000-->正序03E8-->逆序E803
	 *
	 * @param dec
	 *            需要转换的十进制数
	 * @return
	 */
	public static String decToHex_LH(int dec) {
		if (dec < 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 32; i++) {
				// 0x80000000 是一个首位为1，其余位数为0的整数
				int t = (dec & 0x80000000 >>> i) >>> (31 - i);
				sb.append(t);
			}
			String hex = Long.toHexString(Long.parseLong(sb.toString(), 2));
			sb.setLength(0);
			sb.append(hex);
			String[] ss = new String[sb.length() / 2];
			for (int i = 0; i < ss.length; i++) {
				ss[i] = sb.substring(i * 2, (i + 1) * 2);
			}
			Collections.reverse(Arrays.asList(ss));
			sb.setLength(0);
			for (int i = 0; i < ss.length; i++) {
				sb.append(ss[i]);
			}
			return sb.toString();
		} else {
			String hex = "";
			while (dec != 0) {
				String h = Integer.toString(dec & 0xff, 16);
				if ((h.length() & 0x01) == 1) {
					h = '0' + h;
				}
				hex = hex + h;
				dec = dec >> 8;
			}
			return hex;
		}
	}

	/**
	 * 十六进制取反码
	 *
	 * @param hex
	 * @return
	 */
	public static String reverse(String hex) {
		long l = Long.parseLong(hex, 16);
		String s = Long.toBinaryString(l);
		long l1 = ~Long.parseLong(s, 2);
		long l2 = Long.parseLong(String.valueOf(l1));
		String s1 = Long.toHexString(l2);
		return s1.substring(8);
	}

	/**
	 * 根据csn获取M1卡面号
	 *
	 * @param csn
	 */
	public static String getCardNo(String csn) {
		StringBuilder sTmp = new StringBuilder(FormatvalueByLeft(Long.parseLong(csn, 16) + "", "0000000000"));
		try {
			char[] tmp = sTmp.toString().toCharArray();
			System.out.println("chars:" + Arrays.toString(tmp));
			char[] temp1 = new char[tmp.length];
			temp1[0] = tmp[4];
			temp1[1] = tmp[3];
			temp1[2] = tmp[7];
			temp1[3] = tmp[1];
			temp1[4] = tmp[0];
			temp1[5] = tmp[9];
			temp1[6] = tmp[8];
			temp1[7] = tmp[2];
			temp1[8] = tmp[6];
			temp1[9] = tmp[5];
			int[] arr = new int[tmp.length];
			for (int i = 0; i < temp1.length; i++) {
				char c = temp1[i];
				switch (c) {
					case '0':
						temp1[i] = '9';
						break;
					case '1':
						temp1[i] = '2';
						break;
					case '2':
						temp1[i] = '1';
						break;
					case '3':
						temp1[i] = '4';
						break;
					case '4':
						temp1[i] = '3';
						break;
					case '5':
						temp1[i] = '6';
						break;
					case '6':
						temp1[i] = '5';
						break;
					case '7':
						temp1[i] = '8';
						break;
					case '8':
						temp1[i] = '7';
						break;
					case '9':
						temp1[i] = '0';
						break;
				}
				arr[i] = Integer.parseInt(String.valueOf(temp1[i]));
			}
			int a = arr[0] * 7 + arr[1] * 9 + arr[2] * 10 + arr[3] * 5 + arr[4] * 8 + arr[5] * 4 + arr[6] * 2
					+ arr[7] * 1 + arr[8] * 6 + arr[9] * 3;
			int checkCode = 11 - (a % 11);
			int b = 0;
			switch (checkCode) {
				case 1:
					b = 2;
					break;
				case 2:
					b = 3;
					break;
				case 3:
					b = 4;
					break;
				case 4:
					b = 5;
					break;
				case 5:
					b = 6;
					break;
				case 6:
					b = 7;
					break;
				case 7:
					b = 8;
					break;
				case 8:
					b = 9;
					break;
				case 9:
					b = 0;
					break;
				case 10:
					b = 0;
					break;
				case 11:
					b = 1;
					break;
			}
			String cardNo = String.valueOf(temp1) + b;
			System.out.println("cardNo:" + cardNo);
			return cardNo;
		} catch (Exception e) {
			e.printStackTrace();
			return sTmp.toString();
		}
	}

	/**
	 * 将数组的后a位移到前a位，其余顺延
	 *
	 * @param arr
	 *            原数组
	 * @param a
	 *            需要位移的数量
	 * @return 新数组
	 */
	public static int[] moveArray(int[] arr, int a) {
		int[] returnArr = new int[arr.length];
		if (returnArr.length < 1)
			return new int[0];
		for (int i = 0; i < arr.length - a; i++) {
			returnArr[i] = arr[i + a];
		}
		int[] temp = new int[a];
		for (int i = a; i > 0; i--) {
			returnArr[arr.length - i] = arr[i - 1];
		}
		for (int i = 0; i < temp.length; i++) {
			temp[i] = returnArr[returnArr.length - i - 1];
		}
		for (int i = a; i > 0; i--) {
			returnArr[arr.length - i] = temp[a - i];
		}
		return returnArr;
	}

	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}

}