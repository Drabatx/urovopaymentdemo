package com.drabatx.urovopaymentapp.utils

fun String.formatCardNumber(): String {
    val sanitizedNumber = this.filter { it.isDigit() } // Remueve caracteres no numéricos
    return when (sanitizedNumber.length) {
        15 -> { // Formato Amex: 4-6-5
            sanitizedNumber.replaceFirst("(\\d{4})(\\d{6})(\\d{5})".toRegex(), "$1 $2 $3")
        }
        16 -> { // Formato Visa/estándar: 4-4-4-4
            sanitizedNumber.chunked(4).joinToString(" ")
        }
        else -> {
            sanitizedNumber
        }
    }

}