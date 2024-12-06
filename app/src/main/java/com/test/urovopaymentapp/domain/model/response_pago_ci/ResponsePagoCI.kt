package com.test.urovopaymentapp.domain.model.response_pago_ci


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePagoCI(
    val operationResult: OperationResult,
    val servicePayment: ServicePayment
)