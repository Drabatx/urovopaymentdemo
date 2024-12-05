package com.test.urovopaymentapp.utils

import com.test.urovopaymentapp.domain.model.EmvReason

sealed class UrovoResult<out R> {
    data class Success<out T>(val data: T) : UrovoResult<T>()
    data class Error(val exception: Throwable) : UrovoResult<Nothing>()
    data class Message(val emvReason: EmvReason) : UrovoResult<Nothing>()
    object Loading: UrovoResult<Nothing>()
    object Initial: UrovoResult<Nothing>()
}
