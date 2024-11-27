package com.test.urovopaymentapp.domain.model.response.login


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResponse(
    val authenticationData: List<Any>,
    val authenticationState: String
)