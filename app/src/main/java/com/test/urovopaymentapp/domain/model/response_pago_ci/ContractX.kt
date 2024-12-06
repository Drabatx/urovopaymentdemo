package com.test.urovopaymentapp.domain.model.response_pago_ci


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContractX(
    val channelContract: String,
    val chargingCurrency: ChargingCurrency,
    val contractNumber: String,
    val customer: Customer,
    val product: ProductX
)