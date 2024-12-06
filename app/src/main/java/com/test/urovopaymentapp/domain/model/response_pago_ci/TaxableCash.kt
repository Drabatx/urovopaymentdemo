package com.test.urovopaymentapp.domain.model.response_pago_ci


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaxableCash(
    val amount: Double
)