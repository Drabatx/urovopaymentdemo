package com.test.urovopaymentapp.domain.model.response_pago_ci


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sender(
    val accountDetail: String,
    val accountingData: String,
    val channelCode: String,
    val channelUser: String,
    val charge: Charge,
    val chargeAccount: String,
    val chargeAccountMovements: Int,
    val contract: ContractX,
    val operationType: OperationType,
    val serviceNumber: String,
    val terminalId: String
)