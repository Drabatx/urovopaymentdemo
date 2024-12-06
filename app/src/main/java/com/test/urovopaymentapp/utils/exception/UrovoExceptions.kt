package com.test.urovopaymentapp.utils.exception

class UrovoMessageException(
    message: String, cause: Throwable? = null
):Exception(message, cause)

class UrovoSelectApplicationException(
    message: String,
    cause: Throwable? = null
):Exception(message, cause)

class UrovoChecCardResultException(message: String):Exception(message)

class UrovoTransactionException(message: String):Exception(message)
class ConnectServerException(message: String):Exception(message)