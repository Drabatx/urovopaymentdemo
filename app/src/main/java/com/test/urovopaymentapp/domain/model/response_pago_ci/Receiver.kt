package com.test.urovopaymentapp.domain.model.response_pago_ci


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Receiver(
    val accountPaymentMovement: Int,
    val contract: Contract,
    val paymentDetail: String,
    val paymentFinancialData: String
)