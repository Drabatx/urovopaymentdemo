package com.test.urovopaymentapp.utils

sealed class UrovoResult<out R> {
    data class Success<out T>(val data: T) : UrovoResult<T>()
    data class Error(val exception: Throwable) : UrovoResult<Nothing>()
    data class Message(val message: String) : UrovoResult<Nothing>()
    object Loading: UrovoResult<Nothing>()
    object Initial: UrovoResult<Nothing>()
}
