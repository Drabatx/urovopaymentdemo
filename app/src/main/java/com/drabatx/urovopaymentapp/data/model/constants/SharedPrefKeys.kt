package com.drabatx.urovopaymentapp.data.model.pos2.constants

/**
 * Set parameter constants
 */
object SharedPrefKeys {
    const val IsFirstDownloadParams: String = "IsFirstDownloadParams"

    /** Flag to initialize CAPK (IC card public key) table  */
    const val InitCAPKFlag: String = "InitCAPKFlag"

    /** Flags for initializing the POS parameter (EMV) table  */
    const val InitPOSParamsFlag: String = "InitPOSParamsFlag"

    /**Flag to initialize AID table */
    const val InitAIDFlag: String = "InitAIDFlag"

    /**Initialize the flag of the CardBin table */
    const val InitCardBinFlag: String = "InitCardBinFlag"

    /**Initialize the flag of the UrovoUser table */
    const val InitUrovoUserFlag: String = "InitUrovoUserFlag"

    /**The number of MasterKeys for each merchant (step size) */
    const val MK_Index_Num: String = "MK_Index_Num"

    /**The number of MACs for each merchant (step size) */
    const val MAC_Index_Num: String = "MAC_Index_Num"

    /**The number of MAC2 for each merchant (step size) */
    const val MAC2_Index_Num: String = "MAC2_Index_Num"

    /**The number of MAC3 for each merchant (step size) */
    const val MAC3_Index_Num: String = "MAC3_Index_Num"

    /**The number of PinKeys for each merchant (step size) */
    const val PinKey_Index_Num: String = "PinKey_Index_Num"

    /**The number of ENCKs for each merchant (step size) */
    const val ENCK_Index_Num: String = "ENCK_Index_Num"

    /**MK index */
    const val Index_MK: String = "Index_MK"

    /**MAC index */
    const val Index_MAC: String = "Index_MAC"

    /**MAC2 index */
    const val Index_MAC2: String = "Index_MAC2"

    /**MAC3 index */
    const val Index_MAC3: String = "Index_MAC3"

    /**PIN index */
    const val Index_PINKey: String = "Index_PINKey"

    /**ENCK index */
    const val Index_ENCK: String = "Index_ENCK"

    /**Current merchant ID */
    const val CurrentMerchtID: String = "CurrentMerchtID"

    const val SETTINGMENUFALG: String = "SettingMenuFlag"
    //basic settings
    /**Basic settings—merchant number */
    const val BS_MerchantNum: String = "BasicSet_MerchantNum"

    /**Basic settings—merchant Chinese name */
    const val BS_MerchantCNName: String = "BasicSet_MerchantCNName"

    /**Basic settings - Merchant English name */
    const val BS_MerchantEngName: String = "BasicSet_MerchantEngName"

    /**Basic settings - terminal number */
    const val BS_TerminalNum: String = "BasicSet_TerminalNum"

    /**Basic settings - sub-application name */
    const val BS_SubAppName: String = "BasicSet_SubAppName"


    /**Login——Operator account */
    const val BS_UserName: String = "BasicSet_UserName"

    /**Login——operator mobile phone number */
    const val BS_UserPhone: String = "BasicSet_UserPhone"

    //Transaction management settings
    /**Transaction management settings - traditional transactions - used to set whether to support contactless priority */
    const val DS_PRIOITY_CARD: String = "DealSet_PRIOITY_CARD"

    /**Transaction management settings - traditional transactions - used to set whether to support consumer transactions */
    const val DS_Sale: String = "DealSet_Sale"

    /**Transaction management settings - traditional transactions - used to set whether to support consumption cancellation transactions */
    const val DS_SaleRevocate: String = "DealSet_SaleRevocate"

    /**Transaction management settings - traditional transactions - used to set whether to support return transactions */
    const val DS_SaleReturn: String = "DealSet_SaleReturn"

    /**Transaction management settings—traditional transactions—are used to set whether to support balance inquiry transactions.  */
    const val DS_BalanceQuery: String = "DealSet_BalanceQuery"

    /**Transaction management settings—traditional transactions—are used to set whether to support pre-authorized transactions.  */
    const val DS_PreAuth: String = "DealSet_PreAuth"

    /**Transaction management settings—traditional transactions—are used to set whether to support pre-authorization revocation.  */
    const val DS_PreAuthRevocate: String = "DealSet_PreAuthRevocate"

    /**Transaction management settings - traditional transactions - are used to set whether to support pre-authorization completion requests.  */
    const val DS_PreAuthDoneRequest: String = "DealSet_PreAuthDoneRequest"

    /**Transaction management settings—traditional transactions—are used to set whether to support pre-authorization completion notifications.  */
    const val DS_PreAuthDoneNotify: String = "DealSet_PreAuthDoneNotify"

    /**Transaction management settings - traditional transactions - are used to set whether to support pre-authorization to complete revocation.  */
    const val DS_PreAuthDoneRevocate: String = "DealSet_PreAuthDoneRevocate"

    /**Transaction management settings - traditional transactions - used to set whether to support offline settlement transactions */
    const val DS_OfflineDeal: String = "DealSet_OfflineDeal"

    /**Transaction management settings - traditional transactions - used to set whether to support offline adjustment transactions */
    const val DS_OfflineAjust: String = "DealSet_OfflineAjust"

    /**Transaction management settings—electronic transactions—are used to set whether to support contact electronic cash consumption.  */
    const val DS_ContactECashSale: String = "DealSet_ContactECashSale"

    /**Transaction management settings - electronic transactions - used to set whether to support fast payment (non-contact electronic cash consumption)  */
    const val DS_UnContactECashSale: String = "DealSet_UnContactECashSale"

    /**Transaction management settings—electronic transactions—are used to set whether to support designated account transfers of electronic cash.  */
    const val DS_ECashSpecifyLoad: String = "DealSet_ECashSpecifyLoad"

    /**Transaction management settings—electronic transactions—are used to set whether to support non-designated account transfers of electronic cash.  */
    const val DS_ECashUnSpecifyLoad: String = "DealSet_ECashUnSpecifyLoad"

    /**Transaction management settings—electronic transactions—are used to set whether to support electronic cash recharge.  */
    const val DS_ECashCashRecharge: String = "DealSet_ECashCashRecharge"

    /**Transaction management settings—electronic transactions—are used to set whether to support electronic cash recharge and cancellation.  */
    const val DS_ECashCashRechargeRevocate: String = "DealSet_ECashCashRechargeRevocate"

    /**Transaction management settings—electronic transactions—are used to set whether to support electronic cash offline returns.  */
    const val DS_ECashOfflineReturnSale: String = "DealSet_ECashOfflineReturnSale"

    /**Transaction management settings—e-wallet transactions—are used to set whether to support e-wallet consumption.  */
    const val DS_EWalletSale: String = "DealSet_EWalletSale"

    /**Transaction management settings—electronic wallet transactions—are used to set whether to support transfers to specified accounts in electronic wallets.  */
    const val DS_EWalletSpecifyLoad: String = "DealSet_EWalletSpecifyLoad"

    /**Transaction management settings—electronic wallet transactions—are used to set whether to support electronic wallet non-specified account transfers.  */
    const val DS_EWalletUnSpecifyLoad: String = "DealSet_EWalletUnSpecifyLoad"

    /**Transaction management settings - e-wallet transactions - used to set whether to support e-wallet cash recharge.  */
    const val DS_EWalletCashRecharge: String = "DealSet_EWalletCashRecharge"

    const val EC_SWITH: String = "EC_SWITH" //Non-contact transaction channel switch
    const val EC_TIME: String = "EC_TIME" // Flash card re-swipe time
    const val EC_TIME2: String = "EC_TIME2" //The flash card record can be refreshed time
    const val EC_MONEY: String = "EC_MONEY" //Non-contact business password-free limit
    const val EC_FLAG: String = "EC_FLAG" //Non-contact fast label //Password-free label
    const val EC_BIN_A: String = "EC_BIN_A" //Non-connected bin mark A
    const val EC_BIN_B: String = "EC_BIN_B" //Non-connected bin mark B
    const val EC_CDCVM: String = "EC_CDCVM" //CDCVM mark
    const val EC_LIMIT_MONEY: String = "EC_LIMIT_MONEY" //Visa-free limit
    const val EC_LINIT_FLAG: String = "EC_LINIT_FLAG" //Tag-free label

    /**Transaction management settings—installment transactions—are used to set whether to support installment consumption.  */
    const val DS_InstallmentSale: String = "DealSet_InstallmentSale"

    /**Transaction management settings—installment transactions—are used to set whether to support installment payment cancellation.  */
    const val DS_InstallmentSaleRevocate: String = "DealSet_InstallmentSaleRevocate"

    /**Transaction management settings—points-type transactions—are used to set whether to support alliance point consumption.  */
    const val DS_AllianceSale: String = "DealSet_AllianceSale"

    /**Transaction management settings—points-based transactions—are used to set whether to support card issuer point consumption.  */
    const val DS_IssuingBankSale: String = "DealSet_IssuingBankSale"

    /**Transaction management settings - points transactions - are used to set whether to support the withdrawal of alliance points consumption.  */
    const val DS_AllianceSaleRevocate: String = "DealSet_AllianceSaleRevocate"

    /**Transaction management settings—points-type transactions—are used to set whether to support the cancellation of points consumption by the card issuer.  */
    const val DS_IssuingBankSaleRevocate: String = "DealSet_IssuingBankSaleRevocate"

    /**Transaction management settings—points-type transactions—are used to set whether to support alliance points query.  */
    const val DS_AllianceSaleQuery: String = "DealSet_AllianceSaleQuery"

    /**Transaction management settings—points transactions—are used to set whether to support alliance point returns.  */
    const val DS_AllianceSaleReturn: String = "DealSet_AllianceSaleReturn"

    /**Transaction management settings - mobile phone chip transaction switch - used to set whether to support mobile phone consumption.  */
    const val DS_PhoneSale: String = "DealSet_PhoneSale"

    /**Transaction management settings - mobile phone chip transaction switch - used to set whether to support mobile phone consumption cancellation.  */
    const val DS_PhoneSaleRevocate: String = "DealSet_PhoneSaleRevocate"

    /**Transaction management settings - mobile phone chip transaction switch - used to set whether to support mobile phone returns  */
    const val DS_PhoneSaleReturn: String = "DealSet_PhoneSaleReturn"

    /**Transaction management settings - mobile phone chip transaction switch - used to set whether to support mobile phone pre-authorization.  */
    const val DS_PhonePreAuth: String = "DealSet_PhonePreAuth"

    /**Transaction management settings - mobile phone chip transaction switch - used to set whether to support mobile phone pre-authorization revocation.  */
    const val DS_PhonePreAuthRevocate: String = "DealSet_PhonePreAuthRevocate"

    /**Transaction management settings - mobile phone chip transaction switch - used to set whether to support mobile phone pre-authorization completion requests  */
    const val DS_PhonePreAuthDoneRequest: String = "DealSet_PhonePreAuthDoneRequest"

    /**Transaction management settings - mobile phone chip transaction switch -. Used to set whether to support mobile phone pre-authorization completion notification.  */
    const val DS_PhonePreAuthDoneNotify: String = "DealSet_PhonePreAuthDoneNotify"

    /**Transaction management settings - mobile phone chip transaction switch - used to set whether to support mobile phone pre-authorization to complete revocation  */
    const val DS_PhonePreAuthDoneRevocate: String = "DealSet_PhonePreAuthDoneRevocate"

    /**Transaction management settings - mobile phone chip transaction switch - used to set whether to support mobile phone balance inquiry.  */
    const val DS_PhoneBalanceQuery: String = "DealSet_PhoneBalanceQuery"

    /**Transaction management settings - reservation transaction switch - used to set whether to support installment payment consumption  */
    const val DS_PreInstallmentSale: String = "DealSet_PreInstallmentSale"

    /**Transaction management settings - reservation transaction switch - used to set whether to support installment payment consumption cancellation.  */
    const val DS_PreInstallmentSaleRevocate: String = "DealSet_PreInstallmentSaleRevocate"

    /**Transaction management settings - subscription transaction switch - used to set whether to support subscription consumption.  */
    const val DS_SubscribeSale: String = "DealSet_SubscribeSale"

    /**Transaction management settings - Subscription transaction switch - used to set whether to support subscription consumption cancellation */
    const val DS_SubscribeSaleRevocate: String = "DealSet_SubscribeSaleRevocate"

    /**Transaction management settings - ordering transaction switch - used to set whether to support order returns */
    const val DS_SubscribeReturn: String = "DealSet_SubscribeReturn"

    /**Transaction management settings - Subscription transaction switch - used to set whether to support subscription pre-authorization.  */
    const val DS_SubscribePreAuth: String = "DealSet_SubscribePreAuth"

    /**Transaction management settings - Subscription transaction switch - used to set whether to support subscription pre-authorization revocation.  */
    const val DS_SubscribePreAuthRevocate: String = "DealSet_SubscribePreAuthRevocate"

    /**Transaction management settings - ordering transaction switch - used to set whether to support order pre-authorization completion request */
    const val DS_SubscribePreAuthDoneRequest: String = "DealSet_SubscribePreAuthDoneRequest"

    /**Transaction management settings - Subscription transaction switch - used to set whether to support subscription pre-authorization completion notification.  */
    const val DS_SubscribePreAuthDoneNotify: String = "DealSet_SubscribePreAuthDoneNotify"

    /**Transaction management settings - Subscription transaction switch - used to set whether to support subscription pre-authorization completion withdrawal */
    const val DS_SubscribePreAuthDoneRevocate: String = "DealSet_SubscribePreAuthDoneRevocate"

    /**Transaction management settings - other types of transaction switches - used to set whether to support magnetic stripe card cash recharge */
    const val DS_MagCardCachRecharge: String = "DealSet_MagCardCachRecharge"

    /**Transaction management settings - other types of transaction switches - used to set whether to support magnetic stripe card account recharge.  */
    const val DS_MagCardAccountRecharge: String = "DealSet_DS_MagCardAccountRecharge"

    /**Transaction management settings - transaction password input control - used to set whether to support consumption cancellation and whether to input password */
    const val DS_SaleRevocatePwd: String = "DealSet_SaleRevocatePwd"

    /**Transaction management settings - transaction password input control - used to set whether to support pre-authorization revocation and whether to input password */
    const val DS_PreRevocatePwd: String = "DealSet_PreRevocatePwd"

    /**Transaction management settings - transaction password input control - used to set whether to support pre-authorization, complete revocation and whether to input password */
    const val DS_PreAuthDoneRevocatePwd: String = "DealSet_PreAuthDoneRevocatePwd"

    /**Transaction management settings - transaction password input control - used to set whether to support pre-authorization completion (request) and whether to input password */
    const val DS_PreAuthDoneRequstPsw: String = "DS_PreAuthDoneRequstPsw"

    /**Transaction management settings - transaction card swiping control - used to set whether to support consumption cancellation and whether to swipe the card (including all consumption categories)  */
    const val DS_SaleRevocateSwipe: String = "DealSet_SaleRevocateSwipe"

    /**Transaction management settings—Transaction card swiping control—Pre-authorization complete revocation whether to swipe the card */
    const val DS_PreAuthRevocateSwipe: String = "DS_PreAuthRevocateSwipe"

    /**Transaction management settings - settlement transaction control - used to set whether to support settlement and automatic check-out.  */
    const val DS_SettlementAutoSignoOut: String = "DealSet_SettlementAutoSignoOut"

    /**Transaction management settings - settlement transaction control - used to set whether to support settlement and print details.  */
    const val DS_SettlementPrintDetail: String = "DealSet_SettlementPrintDetail"

    /**Transaction management settings - offline transaction control - used to set the offline online delivery method.  */
    const val DS_OffLineUpLoadType: String = "DealSet_OffLineUpLoad"

    /**Transaction management settings - offline transaction control - used to set the number of offline online deliveries */
    const val DS_OffLineUpLoadTimes: String = "DealSet_OffLineUpLoadTimes"

    /**Transaction management settings - offline transaction control - used to set the cumulative number of transactions to be automatically uploaded.  */
    const val DS_AutoUpLoadTotalTimes: String = "DealSet_AutoUpLoadTotalTimes"

    /**Transaction management settings - other transaction controls - used to set whether to enter the supervisor password to cancel/return transactions.  */
    const val DS_RevocateAdminPwdSaleReturn: String = "DealSet_RevocateAdminPwdSaleReturn"

    /**Transaction management settings - other transaction controls - are used to set whether to allow manual input of card numbers.  */
    const val DS_ManualInputCardNum: String = "DealSet_ManualInputCardNum"

    /**Transaction management settings - other transaction controls - are used to set whether to support default card transactions.  */
    const val DS_DefaultSwipeDeal: String = "DealSet_DefaultSwipeDeal"

    /**Transaction management settings - maximum return amount setting - used to set the maximum return amount.  */
    const val DS_MaxSaleReturnAmt: String = "DealSet_MaxSaleReturnAmt"

    //System parameter settings
    /**System parameter setting - used to set serial number */
    const val SS_TrackingNum: String = "SYSSet_TrackingNum"

    /**System parameter setting - used to set the batch number.  */
    const val SS_BatchNum: String = "SYSSet_BatchNum"

    /**System parameter settings - used to set whether to print the Chinese acquiring line */
    const val SS_PrintCNAcquirerBank: String = "SYSSet_PrintCNAcquirerBank"

    /**System parameter setting - used to set whether to print the Chinese card issuing bank.  */
    const val SS_PrintCNIssuingBank: String = "SYSSet_PrintCNIssuingBank"

    /**System parameter setting - used to set the number of thermal printing connections.  */
    const val SS_ThermalPrinterNum: String = "SYSSet_ThermalPrinterNum"

    /**System parameter settings - used to set whether the purchase order is printed in English */
    const val SS_PrintEngSalesSlip: String = "SYSSet_PrintEngSalesSlip"

    /**System parameter settings - used to set the number of retransmissions */
    const val SS_ReSendTimes: String = "SYSSet_PrintEngTimes"

    /**System parameter setting - used to set the receive timeout to be corrected immediately.  */
    const val SS_OvertimeReversal: String = "SYSSet_OvertimeReversal"

    /**System parameter settings - number of corrections */
    const val SS_ReversalTimes: String = "SYSSet_ReversalTimes"

    /**System parameter settings - used to set the maximum number of transactions */
    const val SS_MaxTransactions: String = "SYSSet_MaxTransactions"

    /**System parameter setting - used to set internal and external password keyboard */
    const val SS_InAndOutKeyboard: String = "SYSSet_InAndOutKeyboard"

    /**System parameter settings - used to set internal and external non-contacts */
    const val SS_InAndOutSwipe: String = "SYSSet_InAndOutSwipe"

    /**System parameter settings - used to set external contactless settings */
    const val SS_OutSwipe: String = "SYSSet_OutSwipe"

    /**System parameter setting - used to set the serial port number of the non-connected device */
    const val SS_SwipeDeviceSerial: String = "SYSSet_SwipeDeviceSerial"

    /**System parameter settings - used to set the external non-connected baud rate */
    const val SS_SwipeBaudRate: String = "SYSSet_SwipeBaudRate"

    /**System parameter settings - used to set the tip ratio */
    const val SS_TipRatio: String = "SYSSet_TipRatio"

    /**System parameter settings - used to set the negative sign for cancellation transactions */
    const val SS_RevocateMinus: String = "SYSSet_RevocateMinus"

    /**System parameter settings - used to set the forced download blacklist.  */
    const val SS_ForceLoadBlacklist: String = "SYSSet_ForceLoadBlacklist"


    //Communication parameter settings
    /**Communication parameter setting - used to set TPDU.  */
    const val CS_Tpdu: String = "COMMSet_Tpdu"

    /**Communication parameter setting - used to set the communication method.  */
    const val CS_COMMType: String = "COMMSet_COMMType"

    /**Communication parameter setting - dialing parameter setting - used to set phone number 1.  */
    const val CS_PhoneNum1th: String = "COMMSet_PhoneNum1th"

    /**Communication parameter setting - dialing parameter setting - used to set phone number 2.  */
    const val CS_PhoneNum2th: String = "COMMSet_PhoneNum2th"

    /**Communication parameter setting - dialing parameter setting - used to set phone number 3.  */
    const val CS_PhoneNum3th: String = "COMMSet_PhoneNum3th"

    /**Communication parameter setting - dialing parameter setting - used to set the outside line number.  */
    const val CS_ExteriorNum: String = "COMMSet_ExteriorNum"

    /**Communication parameter setting - dialing parameter setting - used to set whether to pre-dial.  */
    const val CS_PreDialing: String = "COMMSet_PreDialing"

    /** Communication parameter settings - GPRS parameter settings - used to set whether the wireless connection is long.  */
    const val CS_GPRSWireLessKeepAlive: String = "COMMSet_GPRSWireLessKeepAlive"

    /**Communication parameter setting - GPRS parameter setting - used to set the access point.  */
    const val CS_GPRSAccessPoint: String = "COMMSet_GPRSAccessPoint"

    /**Communication parameter setting-GPRS parameter setting--used to set APN number */
    const val CS_GPRSAPNNum: String = "COMMSet_GPRSAPNNum"

    /**Communication parameter setting - GPRS parameter setting - used to set the host IP address.  */
    const val CS_GPRSHostIpAddr: String = "COMMSet_GPRSHostIpAddr"

    /**Communication parameter setting - GPRS parameter setting - used to set the host port.  */
    const val CS_GPRSHostPort: String = "COMMSet_GPRSHostPort"

    /** Communication parameter setting - GPRS parameter setting - used to set the IP address of the device.  */
    const val CS_GPRSBackUpHostIp: String = "COMMSet_GPRSBackUpHostIp"

    /**Communication parameter setting--GPRS parameter setting--used to set the standby port */
    const val CS_GPRSBackUpHostPort: String = "COMMSet_GPRSBackUpHostPort"

    /**Communication parameter setting-CDMA parameter setting--used to set whether the wireless is a long connection */
    const val CS_CDMAWireLessKeepAlive: String = "COMMSet_CDMAWireLessKeepAlive"

    /**Communication parameter setting--CDMA parameter setting--used to set the access point.  */
    const val CS_CDMAAccessPoint: String = "COMMSet_CDMAAccessPoint"

    /**Communication parameter setting - CDMA parameter setting - used to set the host IP.  */
    const val CS_CDMAHostIpAddr: String = "COMMSet_CDMAHostIpAddr"

    /** Communication parameter setting - CDMA parameter setting - used to set the host port.  */
    const val CS_CDMAHostPort: String = "COMMSet_CDMAHostPort"

    /** Communication parameter setting - CDMA parameter setting - used to set the IP of the standby machine.  */
    const val CS_CDMABackUpHostIp: String = "COMMSet_CDMABackUpHostIp"

    /**Communication parameter setting--CDMA parameter setting--used to set the standby port */
    const val CS_CDMABackUpHostPort: String = "COMMSet_CDMABackUpHostPort"

    /** Communication parameter setting--CDMA parameter setting--used to set the user name.  */
    const val CS_CDMAUserName: String = "COMMSet_CDMAUserName"

    /**Communication parameter setting - CDMA parameter setting - used to set user password.  */
    const val CS_CDMAUserPwd: String = "COMMSet_CDMAUserPwd"

    /**Communication parameter setting - Ethernet parameter setting - used to set the host IP.  */
    const val CS_EthernetHostIp: String = "COMMSet_EthernetHostIp"

    /** Communication parameter setting - Ethernet parameter setting - used to set the network management IP.  */
    const val CS_EthernetWebMasterIp: String = "COMMSet_EthernetWebMasterIp"

    /** Communication parameter setting--Ethernet parameter setting--. Used to set the subnet mask.  */
    const val CS_EthernetSubnetMask: String = "COMMSet_EthernetSubnetMask"

    /**Communication parameter setting - Ethernet parameter setting - used to set the server IP.  */
    const val CS_EthernetServerIp: String = "COMMSet_EthernetServerIp"

    /**Communication parameter setting - Ethernet parameter setting - used to set the server port.  */
    const val CS_EthernetServerPort: String = "COMMSet_EthernetServerPort"

    /**Communication parameter setting - Ethernet parameter setting - used to set the backup server IP.  */
    const val CS_EthernetBackServerIp: String = "COMMSet_EthernetBackServerIP"

    /** Communication parameter setting - Ethernet parameter setting - used to set the backup server port.  */
    const val CS_EthernetBackServerPort: String = "COMMSet_EthernetBackServerPort"

    /**Communication parameter settings - other communication parameter settings - used to set communication waiting time (seconds). string type  */
    const val CS_WaitTime: String = "COMMSet_WaitTime"

    /**Communication parameter settings - other communication parameter settings - used to set the number of input redials.  */
    const val CS_InputReDialTimes: String = "COMMSet_InputReDialTimes"

    //Terminal key management
    /**Terminal key management-IC card introduction key-used to view merchant number and.  */
    const val ES_MerchantNum: String = "EncySet_MerchantNum"

    /**Terminal key management - IC card import key - used to set the input key number 0-99.  */
    const val ES_InputEncryNum0To99: String = "EncySet_InputEncryNum0To99"

    /**Terminal key management--IC card import key--Insert key IC card */
    const val ES_InsertEncryIc: String = "EncySet_InsertEncryIc"

    /**Terminal key management - IC card import key - used to enter the password, the default is 12345678.  */
    const val ES_InputPwd: String = "EncySet_InputPwd"

    /**Terminal key management-IC card import key-key importing.  */
    const val ES_ImptingEncryKey: String = "EncySet_ImptingEncryKey"

    /**Terminal key management - IC card key import - key import successful.  */
    const val ES_ImptEncryKeySuc: String = "EncySet_ImptEncryKeySuc"

    /**Terminal key management - IC card import key - merchant terminal does not match, key import fails.  */
    const val ES_ImptEncryKeyFailMisTermial: String = "EncySet_ImptEncryKeyFailMisTermial"

    /**Terminal key management-key index setting--used to set the key index.  */
    const val ES_EncryKeyIndex: String = "EncySet_EncryKeyIndex"

    /**Terminal key management - set DES algorithm */
    const val ES_EncryKeyAlgorithm: String = "EncySet_EncryKeyAlgorithm"

    /**Terminal key management - master key settings - enter the key number.  */
    const val ES_InputEncryKeyNum: String = "EncySet_InputEncryKeyNum"

    /**Terminal key management - master key settings - key value (3DES: 32 bits; DES: 16 bits).  */
    const val ES_EncryKeyVal: String = "EncySet_EncryKeyVal"

    /**Terminal key management - master key settings - hand-entered key download successful.  */
    const val ES_EncryKeyDownSuc: String = "EncySet_EncryKeyDownSuc"


    //Password management
    /**Password management - used to set new system passwords.  */
    const val PS_NewSysPwd: String = "PwdSet_NewSysPwd"

    /**Password management - Enter the new system password again.  */
    const val PS_ReInputNewSysPwd: String = "PwdSet_ReInputNewSysPwd"

    /**Password management - used to set new secure passwords.  */
    const val PS_NewSafePwd: String = "PwdSet_NewSafePwd"

    /**Password Management - Enter a new secure password again.  */
    const val PS_ReInputNewSafePwd: String = "PwdSet_ReInputNewSafePwd"


    //Other function settings
    /**Other function settings - clear transaction flow - used to set the clearing mark */
    const val ExS_ClsReversalFlag: String = "ExtraSet_ClsReversalFlag"

    /**Other function settings - clear transaction flow - used to set clear transaction flow.  */
    const val ExS_ClsTransSerial: String = "ExtraSet_ClsTransSerial"

    /**Other function settings - clear transaction flow - used to set clear processing requirements.  */
    const val ExS_ClsDealRequest: String = "ExtraSet_ClsDealRequest"

    /**Other function settings - clear transaction flow - used to set clear script notifications.  */
    const val ExS_ClsScriptNotify: String = "ExtraSet_ClsScriptNotify"

    /**Other function settings - clear transaction flow - used to set clear blacklist.  */
    const val ExS_ClsBlackList: String = "ExtraSet_ClsBlackList"

    /**Other function settings - purchase order printing settings - POS remaining storage space  */
    const val ExS_FreeCapacity: String = "ExtraSet_FreeCapacity"

    /**Other function settings - clear transaction flow - used to set the purchase order header to display LOGO or Chinese.  */
    const val ExS_SalesSlipTitleType: String = "ExtraSet_SalesSlipTitleType"

    /**Other function settings - Clear transaction flow--. Used to set the purchase order header when the header is in Chinese  */
    const val ExS_SalesSlipTitle: String = "ExtraSet_SalesSlipTitle"

    /**Other function settings - clear transaction flow - used to set service hotline  */
    const val ExS_ServiceHotline: String = "ExtraSet_ServiceHotline"

    /**Other function settings - clear transaction flow - used to set the purchase order font (small, medium, large).  */
    const val ExS_SalesSlipFontSize: String = "ExtraSet_SalesSlipFontSize"

    /**Other function settings--download function--for echo function */
    const val ExS_Resound: String = "ExtraSet_Resound"

    /**Other function settings - download function - used for parameter transfer */
    const val ExS_ParamTransmit: String = "ExtraSet_ParamTransmit"

    /**Other function settings - download function - used to download IC card public key */
    const val ExS_DownIcPubKey: String = "ExtraSet_DownIcPubKey"

    /**Other function settings - download function - used to download IC card parameters */
    const val ExS_DownIcParam: String = "ExtraSet_DownIcParam"

    /**Other function settings - parameter printing - used to print merchant information */
    const val ExS_PrintMerchantInfo: String = "ExtraSet_PrintMerchantInfo"

    /**Other function settings - parameter printing - used to print transaction control information.  */
    const val ExS_PrintControlInfo: String = "ExtraSet_PrintControlInfo"

    /**Other function settings - parameter printing - used to print system control information.  */
    const val ExS_PrintSysControInfo: String = "ExtraSet_PrintSysControInfo"

    /**Other function settings - parameter printing - used to print communication parameter information.  */
    const val ExS_PrintCommParamInfo: String = "ExtraSet_PrintCommParamInfo"

    /**Other function settings - parameter printing - used to print version information  */
    const val ExS_PrintVersionInfo: String = "ExtraSet_PrintVersionInfo"

    /**Other function settings -- parameter printing -- used to print other information.  */
    const val ExS_PrintExtraInfo: String = "ExtraSet_PrintExtraInfo"

    /**Other function settings - printing when the issuing bank code is unknown - used to set printing when the issuing bank code is unknown.  */
    const val ExS_PrintUnknownBankCode: String = "ExtraSet_PrintUnknownBankCode"

    /**
     * Whether electronic signature requires entering mobile phone number
     */
    const val SIGN_PHONE_TAG: String = "SIGN_PHONE_TAG"
}
