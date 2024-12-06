package com.test.urovopaymentapp.domain.model.response_pago_ci


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OperationResult(
    val id: String,
    val indicatorStatusOperation: String,
    val paymentTrade: String,
    val sequenceReference: String
)