package com.drabatx.urovopaymentapp.data.model.pos2.Constants


/**
 * Comparison table of error codes returned by the driver layer
 * @author LAIMIGN
 */
object RspCode {
    const val RSPOK: Int = 0 //Write key successfully
    const val RSPERR: Int = -1 //Failed to write key
    const val REFER_APPROVE: Int = 0x01 //Reference return code (select approval)
    const val REFER_DENIAL: Int = 0x02 //Reference return code (select rejection)
    const val ONLINE_APPROVE: Int = 0x00 //Online return code (online approval)
    const val ONLINE_FAILED: Int = 0x01 //Online return code (online failure)
    const val ONLINE_REFER: Int = 0x02 //Online return code (online reference)
    const val ONLINE_DENIAL: Int = 0x03 //Online return code (online rejection)
    const val ONLINE_ABORT: Int = 0x04 //Compatible with PBOC (transaction termination)
    const val EMV_OK: Int = 0 //Success
    const val ERR_EMVRSP: Int = -101 //return code error
    const val ERR_APPBLOCK: Int = -102 //The application is locked
    const val ERR_NOAPP: Int = -103 //There is no EMV application in the card
    const val ERR_USERCANCEL: Int = -104 //User cancels current operation or transaction
    const val ERR_TIMEOUT: Int = -105 //User operation timeout
    const val ERR_EMVDATA: Int = -106 //Card data error
    const val ERR_NOTAACCEPT: Int = -107 //Transaction not accepted
    const val ERR_EMVDENIAL: Int = -108 //Transaction rejected
    const val ERR_KEYEXP: Int = -109 //Key expiration
    const val ERR_NOPINPAD: Int =
        -110 //There is no password keyboard or the keyboard is unavailable
    const val ERR_NOPIN: Int = -111 //There is no password or the user ignored the password input
    const val ERR_CAPKCHECKSUM: Int = -112 //Certification center key checksum error
    const val ERR_NOTFOUND: Int = -113 //The specified data or element was not found
    const val ERR_NODATA: Int = -114 //The specified data element has no data
    const val ERR_OVERFLOW: Int = -115 //Memory overflow
    const val ERR_NOTRANSLOG: Int = -116 //No transaction log
    const val ERR_NORECORD: Int = -117 //No record
    const val ERR_NOLOGITEM: Int = -118 //NOLOGITEM error
    const val ERR_ICCRESET: Int = -119 //IC card reset failed
    const val ERR_ICCCMD: Int = -120 //IC command failed
    const val ERR_ICCBLOCK: Int = -121 //IC card lock card
    const val ERR_ICCNORECORD: Int = -122 //IC card has no record
    const val ERR_GENAC1_6985: Int = -123 //GEN AC command returns 6985
    const val ERR_USECONTACT: Int = -124 //Non-connection failure, use the contact interface instead
    const val ERR_APPEXP: Int = -125 //qPBOC card application expires
    const val ERR_BLACKLIST: Int = -126 //qPBOC blacklist card
    const val ERR_GPORSP: Int = -127 //err from GPO
    const val ERR_TRANSEXCEEDED: Int = -128 //Non-contact transaction exceeds limit
    const val ERR_QPBOCFDDAFAIL: Int = -129 //Non-connection qPBOC fDDA failed
    const val ERR_EC_REJECT: Int = -143 ///Electronic cash transaction rejected
    const val ERR_EC_BLANCE_NOT_ENOUGH: Int = -144 //Insufficient balance
    const val _OK: Int = 0 //Success
    const val _NORMAL_ERROR: Int = -1 //General error
    const val _SOCKER_CONNECT_ERROR: Int = -2000 //Failed to establish network connection
    const val _SOCKER_SEND_TIMEOUT_ERROR: Int = -2001 //Send timeout
    const val _SOCKER_SEND_ERROR: Int = -2002 //Send error
    const val _SOCKER_RECEIVE_ERROR: Int = -2003 //Receive error
    const val _SOCKER_RECEIVE_TIMEOUT_ERROR: Int = -2004 //Receive timeout
    const val _PCI_WRITEMK_ERROR: Int = -2005 //Wrong writing master key
    const val _PCI_WRITEENCRITK_ERROR: Int = -2006 //Wrong writing encryption key
    const val _PCI_WRITEPINK_ERROR: Int = -2007 //Wrong writing PINK
    const val _PCI_WRITEMACK_ERROR: Int = -2008 //Write MACK error
    const val _PCI_CRCMK_ERROR: Int = -2009 //Wrong writing master key
    const val _PCI_CRCENCRITK_ERROR: Int = -2010 //Wrong writing encryption key
    const val _PCI_CRCPINK_ERROR: Int = -2011 //Wrong writing PINK
    const val _PCI_CRCMACK_ERROR: Int = -2012 //Wrong writing MACK
    const val _PCI_MAXQ1850_ERROR: Int = -2013 //1850 operation failed
    const val _ISSUR_DATAS_AUTH_ERROR: Int = -2014 //Card issuing bank data authentication failed
    const val _PROC_SCRIPT_ERROR: Int = -2015 //Script processing failed
    const val _PCI_OPER_MASTKEY_ERROR: Int = -2016 //Master key operation error
    const val _PCI_OPENCOMM_ERROR: Int = -2017 //Pci Port Open err
    const val _E_MAXQ1850_ERROR: Int = -2018 //1850Work Err
    const val _E_TRANSREC_FULL: Int = -2019 //Trans Rec Full.
    const val _OPER_NAME_LENG_ERROR: Int = -2020 //Operator name length error
    const val _OPER_KEY_LENG_ERROR: Int = -2021 //Operator password length is wrong
    const val _OPER_FIND_OUT_ERROR: Int = -2022 //Operator not found
    const val _OPER_NAME_EXIST_ERROR: Int = -2023 //The operator to be modified already exists
    const val _OPER_VERIFY_ERROR: Int = -2024 //Verification error
    const val _OPER_KEY_ERROR: Int = -2025 //Wrong password
    const val _Need_Edit_ManKey: Int = -2026 //Need to change administrator password
    const val _Need_Edit_OperKey: Int = -2027 //The operator password needs to be modified
    const val _E_COURIER_UNSAME: Int = -2028 //The courier is different

    const val _E_ERR_SWIPE: Int = -2029 //Card swipe error
    const val _E_TIMEOUT_SWIPE: Int = -2030 //Card swipe timeout
    const val _E_ERR_EXTRACTPAN: Int = -2031 //Account retrieval error

    const val _E_READ_PINKEY_ERROR: Int = -2032 //Wrong reading PINKEY

    const val _E_PACK8583_ERROR: Int = -2033 //Packaging error
    const val _E_UNPACK8583_ERROR: Int = -2034 //Unpacking error
    const val _E_UNPACK8583DATAS_ERROR: Int = -2035 //Error in unpacking a specific data

    const val _E_PACK8583_MAC: Int = -2036 //MAC error
    const val _E_DB_NLL: Int = -2037 //The database object is empty or not open
    const val _E_DB_OPERATION: Int = -2038 //Database operation failed

    const val _E_PINKEY_ACTION_ERROR: Int = -2039 //PINKEY operation error
    const val _E_ENCKEY_ACTION_ERROR: Int = -2040 //Track encryption error
    const val _E_NO_TRACKDATAS_ERROR: Int = -2041 //No track data

    const val _E_FIELD39_IST_ZERO: Int = -2042 //39 field is not 0
    const val _NEED_INPUT_ONLINE_PIN: Int = 50 //Confidential text needs to be entered
    const val _NEED_INPUT_OFFPLAIN_PIN: Int = 51 //Need to enter offline plain text
    const val _NEED_INPUT_OFFENCRY_PIN: Int = 52 //Need to enter confidential text

    const val ERR_ISSUR_AUTH: Int = -147 //Card issuing bank data authentication failed
    const val ERR_PROC_SCRIPT: Int = -148 //Script processing failed

    const val ERR: Int = -2043 //error

    const val UEMV_OK: Int = 0
    const val UEMV_TRANSEXCEEDED: Int = (-231) //Non-contact transaction exceeds limit
    const val UEMV_GPORSP: Int = (-230) //GPO failed
    const val UEMV_EC_DECLINED: Int = (-229) //Electronic cash offline rejection
    const val UEMV_APP_NOACCEPT: Int = (-228) //The application does not accept); can be reselected
    const val UEMV_SCRIPT_FAIL: Int = (-227) //Card issuing bank script execution failed
    const val UEMV_ARPC_FAIL: Int = (-226) //Card issuing bank authentication failed
    const val UEMV_DECLINED: Int = (-225) //Transaction rejected
    const val UEMV_CANCEL: Int = (-224) //Transaction cancellation
    const val UEMV_ONLINE: Int = (-223) //Transaction should be online
    const val UEMV_CVM_FAIL: Int = (-222) //Cardholder verification failed
    const val UEMV_APP_EXPIRED: Int = (-221) //The application has expired
    const val UEMV_APP_EFFECT: Int = (-220) //The application has not yet taken effect
    const val UEMV_AUTH_FAIL: Int = (-219) //Data authentication failed
    const val EMV_FALLBACK: Int = (-218) //Transaction fallback
    const val UEMV_APP_BLOCK: Int = (-217) //The application is locked
    const val UEMV_NOAPP: Int = (-216) //No common application
    const val UEMV_PARA_ERR: Int = (-215) //Wrong parameter
    const val UEMV_CARD_BLOCK: Int = (-214) //The card is locked
    const val UEMV_COMMAND_FAIL: Int = (-213) //Card reading failed
    const val UEMV_CARD_REMOVED: Int = (-212) //The card was removed during the transaction
    const val UEMV_FAIL: Int = (-211) //Transaction failed, execution failed
    const val UEMV_OTHER_INTERFACE: Int = (-209) //Please try other communication interfaces
    const val UEMV_QPBOC_OFFLINE: Int = (-208) //Contactless QPBOC transactions are accepted offline
    const val UEMV_QPBOC_ONLINE: Int = (-207) //Contactless PBOC transaction online
    const val UEMV_PBOC_ONLINE: Int = (-206) //Contactless PBOC transaction online
    const val UEMV_MSD_ONLINE: Int = (-205) //Contactless MSD transaction online
    const val UEMV_EC_ACCEPT: Int = (-204) //Electronic cash offline acceptance
    const val UEMV_OFFLINE_ACCEPT: Int = (-203) //Standard process offline acceptance
    const val UEMV_ICCCMD: Int = (-202) //IC card command failed
    const val UEMV_ICCRESET: Int = (-201) //IC card reset failed
    const val UEMV_TIMEOUT: Int = (-200) //Timeout
    const val UEMV_NOTAACCEPT: Int = (-199) //Transaction not accepted
    const val UEMV_EMVRSP: Int = (-198) //Return code error
    const val UEMV_NODATA: Int = (-197) //The specified data element has no data
    const val UEMV_DATA_EXIST: Int = (-198) //EMV data already exists
    const val UEMV_NOTFOUND: Int = (-197) //The specified data or element was not found
    const val UEMV_ONLINE_F55: Int = (-196) //The 55 field data returned by the background is wrong
    const val UEMV_ISSUR_AUTH: Int = (-195) //Card issuing bank data authentication failed
    const val UEMV_PROC_SCRIPT: Int = (-194) //Script processing failed
    const val UEMV_ICCNORECORD: Int = (-193) //IC card has no record
    const val UEMV_PINBLOCK: Int = (-192) //PIN lock
    const val UEMV_OVERFLOW: Int = (-191) //Memory overflow
    const val UEMV_APPEXP: Int = (-190) //qPBOC card application expires
    const val UEMV_EC_BLANCE_NOT_ENOUGH: Int = (-189) //Insufficient balance
    const val UEMV_BLACKLIST: Int = (-188) //qPBOC blacklist card

    //public final static int UEMV_NEED_ONLINE = (-187); //Due to the need to enter the online PIN. For example, requesting online transactions
    const val UEMV_QPBOCFDDAFAIL: Int = (-186) //Non-connection qPBOC fDDA failed
    const val UEMV_SWIPE: Int = (-185) //Card swipe error
    const val UEMV_NOPINPAD: Int =
        (-184) //There is no password keyboard or the keyboard is not available

    const val _PCI_MAXQ32550_ERROR: Int = (-1185) //32550 error

    const val _MENUAPPS: Int = 57


    /**The upper eight bits of data are empty */
    const val High8Empty: String = "4000"

    /**The lower 8 bits of data are empty */
    const val Low8Empty: String = "4001"

    /**TLK data is empty */
    const val TlkEmpty: String = "4002"

    /**TMK data is empty */
    const val TmkEmpty: String = "4003"

    /**TAK data is empty */
    const val TakEmpty: String = "4004"

    /**TDK data is empty */
    const val TdkEmpty: String = "4005"

    /**TPK data is empty */
    const val TpkEmpty: String = "4006"

    /**The serial number data is empty */
    const val TraceEmpty: String = "4007"

    /**The batch number data is empty */
    const val BatchEmpty: String = "4008"

    /**The communication timeout data is empty */
    const val CommTimeoutEmpty: String = "4009"

    /**The total number of transactions is empty */
    const val TransTotalEmpty: String = "4010"

    /**The current transaction number data is empty */
    const val CurrTransNumEmpty: String = "4011"

    /**The number of correction data is empty */
    const val ReversalTimesEmpty: String = "4012"

    /**IP address data is empty */
    const val IPEmpty: String = "4013"

    /**Port number data is empty */
    const val PortEmpty: String = "4014"

    /**tpdu data is empty */
    const val TpduEmpty: String = "4015"

    /**Terminal number data is empty */
    const val TermiEmpty: String = "4016"

    /**The merchant number data is empty */
    const val MerchEmpty: String = "4017"

    /**The merchant name data is empty */
    const val MerchNameEmpty: String = "4018"

    /**The EMV parameter data that needs to be downloaded is empty */
    const val DownloadEMVEmpty: String = "4019"

    /**The public key data required to be downloaded is empty */
    const val DownloadPubKeyEmpty: String = "4020"

    /**The maximum return amount data is empty */
    const val MaxRefundEmpty: String = "4021"

    /**Tip switch data is empty */
    const val TipSwitchEmpty: String = "4022"

    /**Tip rate data is empty */
    const val TipRateEmpty: String = "4023"

    /**The current offline transaction number data is empty */
    const val OfflineTransEmpty: String = "4024"

    /**Small vote data is empty */
    const val ReceiptNumEmpty: String = "4025"

    /**Manual input card enable data is empty */
    const val ManualInputEmpty: String = "4026"

    /** Whether to cancel the card swipe data is empty  */
    const val RevoIfSwipCardEmpty: String = "4028"

    /** Whether to cancel the password input data is empty  */
    const val RevoIfPswEmpty: String = "4029"

    /**The test correction data is empty */
    const val TestReversalEmpty: String = "4030"

    /**pin encryption error */
    const val EncrptPinError: String = "4031"

    /**Track 2 encryption error */
    const val EncrptTrack2Error: String = "4032"

    /**No track 2 data */
    const val Track2Empty: String = "4033"

    /**Failed to parse amount */
    const val AmtResolveError: String = "4034"

    /**Sending mac encryption error */
    const val EncrptMacError: String = "4035"
}
