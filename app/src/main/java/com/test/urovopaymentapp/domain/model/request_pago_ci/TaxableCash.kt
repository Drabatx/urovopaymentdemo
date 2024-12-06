package com.test.urovopaymentapp.domain.model.request_pago_ci


import kotlinx.serialization.Serializable

@Serializable
data class TaxableCash(
    val amount: String
) {
    class Builder {
        private var amount: String = ""

        fun setAmount(amount: String) = apply { this.amount = amount }

        fun build(): TaxableCash {
            if (amount.isBlank()) throw IllegalArgumentException("Amount is required")
            return TaxableCash(amount)
        }
    }
}