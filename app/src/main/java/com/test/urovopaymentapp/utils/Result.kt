package com.test.urovopaymentapp.utils

import com.test.urovopaymentapp.domain.model.EmvReason

sealed class Result<out R> {
    data class Success<out T>(val data: T) :Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    data class Loading(val message: String = "") : Result<Nothing>()
}
