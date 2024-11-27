package com.test.urovopaymentapp.domain.model.response.login


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BackendUserResponse(
    val accountingTerminal: String,
    val clientId: String,
    val clientStatus: String,
    val userStatus: String,
    val userType: String
)