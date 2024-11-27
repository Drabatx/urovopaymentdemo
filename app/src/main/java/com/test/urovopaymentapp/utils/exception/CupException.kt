package com.test.urovopaymentapp.utils.exception

import android.text.TextUtils

//2. Failed to call PIN verification program
//3. MAC processing failed
//4. Key processing failed. Rejection. The exchange center is abnormal. Please try again later.
/**Exception coding and
 * @author KuCoffee
 */
object CupException {
    /**
     * Get error information based on error code
     */
    private const val errMsg = "Please get the correct error code"
    private var map: MutableMap<String, String> = HashMap()

    init {
        map["4000"] = "The upper eight bits of data are empty"
        map["4001"] = "The lower 8 bits of data are empty"
        map["4002"] = "TLK data is empty"
        map["4003"] = "TMK data is empty"
        map["4004"] = "TAK data is empty"
        map["4005"] = "TDK data is empty"
        map["4006"] = "TPK data is empty"
        map["4007"] = "The serial number data is empty"
        map["4008"] = "The batch number data is empty"
        map["4009"] = "The communication timeout data is empty"
        map["4010"] = "The total number of transactions is empty"
        map["4011"] = "The current transaction number data is empty"
        map["4012"] = "The number of correction data is empty"
        map["4013"] = "ip address data is empty"
        map["4014"] = "Port number data is empty"
        map["4015"] = "tpdu data is empty"
        map["4016"] = "Terminal number data is empty"
        map["4017"] = "The merchant number data is empty"
        map["4018"] = "The merchant name data is empty"
        map["4019"] = "The EMV parameter data that needs to be downloaded is empty"
        map["4020"] = "The public key data to be downloaded is empty"
        map["4021"] = "The maximum return amount data is empty"
        map["4022"] = "Tip switch data is empty"
        map["4023"] = "Tip rate data is empty"
        map["4024"] = "The current number of offline transactions is empty"
        map["4025"] = "The small vote data is empty"
        map["4026"] = "Manual input card enable data is empty"
        map["4028"] = "Whether the card swipe data is empty"
        map["4029"] = "Whether the password input data for revocation is empty"
        map["4030"] = "Test correction data is empty"
        map["4031"] = "pin encryption error"
        map["4032"] = "Track 2 encryption error"
        map["4033"] = "No track 2 data"
        map["4034"] = "Failed to parse amount"
        map["4035"] = "Sending mac encryption error"

        map["-217"] = "Application is locked"
        map["-214"] = "Card is locked"
        map["-225"] = "Transaction rejected "
        map["-224"] = "Transaction canceled"
        map["-216"] = "No common application"
        map["-192"] = "PIN locked"

        map["01"] =
            "Please contact the card issuing bank, or check the card information and re-enter"
        map["02"] = "Please contact the company"
        map["03"] = "Invalid merchant"
        map["04"] = "Invalid terminal"
        map["05"] = "No acceptance, or check the card information and re-enter"
        map["06"] = "Error"
        map["07"] = "Card confiscated under certain conditions"
        map["09"] = "The request is being processed"
        map["12"] = "Invalid transaction, or check the card information and re-enter"
        map["13"] = "Invalid amount, transaction amount is not within the allowed range"
        map["14"] =
            "Invalid card number (no such number), or check the card information and re-enter"
        map["15"] = "No such card issuer"
        map["17"] = "Customer Cancel"
        map["18"] =
            "The tradable amount corresponding to the merchant's margin is insufficient, please contact the company"
        map["19"] =
            "Your account balance is insufficient for return, please recharge to the account before return"
        map["20"] = "Invalid response"
        map["21"] = "No action can be taken"
        map["22"] = "Fault suspected"
        map["23"] = "Unacceptable transaction fee"
        map["25"] = "Original transaction not found"
        map["30"] = "Format error"
        map["31"] = "This card issuing bank is not supported"
        map["32"] = "Cards not accepted by merchants"
        map["33"] = "Expired card"
        map["34"] = "suspected of cheating"
        map["35"] = "Please contact the company"
        map["36"] = "Restricted Card"
        map["37"] = "Risk card, please contact the company"
        map["38"] = "Exceeds allowed trial input"
        map["39"] = "No credit account"
        map["40"] = "The requested function is not supported yet"
        map["41"] = "Report lost card"
        map["42"] = "No such account"
        map["43"] = "Stolen Card"
        map["44"] = "No such investment account"
        map["51"] = "Insufficient funds"
        map["52"] = "No such checking account"
        map["53"] = "No such savings card account"
        map["54"] = "Expired card"
        map["55"] = "Wrong password"
        map["56"] = "No record of this card"
        map["57"] = "Transactions not allowed by the cardholder"
        map["58"] = "Transactions performed by the terminal are not allowed"
        map["59"] = "suspected of cheating"
        map["60"] = "Please contact the company"
        map["61"] = "Withdrawal transfer amount limit exceeded"
        map["62"] = "Restricted Card"
        map["63"] = "Security violation"
        map["64"] = "The original amount is wrong"
        map["65"] = "Withdrawal limit exceeded"
        map["66"] = "Please contact the company"
        map["67"] = "Forced acceptance (requiring the card to be confiscated at the ATM)"
        map["68"] =
            "Unable to obtain transaction response within normal time, please try again later"
        map["75"] = "The number of times allowed to enter the password exceeds the limit"
        map["76"] = "Invalid account"
        map["80"] = "Transaction rejected"
        map["90"] =
            "Processing at the end of the day (the system terminates one day's activities and starts the next day's activities, and the transaction can be sent again in a few minutes)"
        map["91"] = "The card issuer or exchange center cannot operate"
        map["92"] = "The network is temporarily unreachable, please try again later"
        map["93"] = "The transaction is illegal and cannot be completed"
        map["94"] = "Duplicate transaction"
        map["95"] = "Check errors"
        map["96"] = "Rejected, the exchange center is abnormal, please try again later"
        map["97"] = "ATM/POS terminal number cannot be found"
        map["98"] = "The exchange center cannot receive the card issuer's response"
        map["99"] = "Password format is wrong"
        map["A0"] = "MAC verification error, please sign in again"
        map["A1"] = "Transfer currency is inconsistent"
        map["A2"] = "The transaction is successful, please confirm with the card issuing bank"
        map["A3"] = "Account is incorrect"
        map["A4"] = "The transaction is successful, please confirm with the card issuing bank"
        map["A5"] = "The transaction is successful, please confirm with the card issuing bank"
        map["A6"] =
            "The transaction is successful, please confirm with the card issuing bank"
        map["A7"] = "Rejected, the exchange center is abnormal, please try again later"
        map["E3"] = "Identification card error"
        map["E4"] = "Abnormal binding customer status"
        map["E8"] = "Failed to operate the transaction schedule"
        map["E9"] = "Failed to convert response code"
        map["EA"] = "No original payment transaction"
        map["EB"] = "Transaction information error"
        map["EC"] = "No terminal information"
        map["ED"] = "Transaction restricted"
        map["EF"] = "Bill paid"
        map["EG"] = "Payment unsuccessful"
        map["F1"] = "The order number is wrong"
        map["F2"] = "The order date is incorrect"
        map["F6"] = "Server exception, request to be sent again"
        map["F7"] = "Other exceptions"
        map["B1"] = "No entrusting unit information"
        map["B2"] = "No merchant information"
        map["T3"] = "The status of the entrusting unit is abnormal"
        map["S1"] = "Third-party MAC verification failed"
    }

    /** Get the corresponding value of the error code thrown in the program
     * @param errCode error code
     * @return error message
     */
    fun getErrMsg(errCode: String): String? {
        if (TextUtils.isEmpty(errCode)) {
            return errMsg
        }
        return map[errCode]
    }

    /**
     * Get the corresponding value of the error message thrown in the program
     * @param errMsg error message
     * @return
     */
    fun getErrCode(errMsg: String): String {
        if (TextUtils.isEmpty(errMsg)) {
            return "-3"
        }
        for (key in map.keys) {
            val mapValue = map[key]
            if (mapValue == errMsg) return key
        }
        return "-3"
    }
}
