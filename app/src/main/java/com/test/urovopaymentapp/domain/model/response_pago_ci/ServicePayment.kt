package com.test.urovopaymentapp.domain.model.response_pago_ci


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServicePayment(
    val amount: Amount,
    val cieGuide: String,
    val commission: Commission,
    val concept: String,
    val maximumAmount: MaximumAmount,
    val minimumAmount: MinimumAmount,
    val numberAgreement: String,
    val paymentType: String,
    val `receiver`: Receiver,
    val reference: String,
    val sender: Sender,
    val tax: Tax,
    val taxableCash: TaxableCash,
    val totalBalance: TotalBalance
)