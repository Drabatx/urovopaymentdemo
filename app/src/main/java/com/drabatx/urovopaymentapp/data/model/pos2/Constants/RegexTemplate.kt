package com.drabatx.urovopaymentapp.data.model.pos2.Constants


/**
 * Tools to determine the legality of content
 * @author LAIMIGN
 */
object RegexTemplate {
    /**IP address */
    var IP: String =
        "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{ 1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d |2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25 [0-5])$"

    /**Master Key */
    var MasterKey: String = "^([0-9a-fA-F]{16})|([0-9a-fA-F]{32})$"
}
