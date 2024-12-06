package com.test.urovopaymentapp.domain.model.request_pago_ci


import kotlinx.serialization.Serializable

@Serializable
data class ChargingCurrency(
    val code: String="MXP"
) {
    class Builder {
        private var code: String = "MXP"

        fun setCode(code: String) = apply { this.code = code }

        fun build(): ChargingCurrency {
            if (code.isBlank()) throw IllegalArgumentException("Code is required")
            return ChargingCurrency(code)
        }
    }
}