package com.test.urovopaymentapp.utils.exception

/**Customized exception, used to save error code
 *
 */
class ClientException : Exception {
    /**Get exception code
     * @return the errorCode
     */
    var errorCode: String
        private set

    constructor(errorCode: String) : super(errorCode) {
        this.errorCode = errorCode
    }

    constructor(cause: Throwable?, errorCode: String) : super(cause) {
        this.errorCode = errorCode
    }

    companion object {
        private const val serialVersionUID = -2155382375819767054L
    }
}
