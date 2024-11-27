package com.test.urovopaymentapp.domain.model.response.login


import kotlinx.serialization.Serializable

data class GrantingTicketResponse(
    val authenticationResponse: AuthenticationResponse,
    val backendUserResponse: BackendUserResponse
)