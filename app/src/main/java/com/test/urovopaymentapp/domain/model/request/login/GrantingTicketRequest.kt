package com.test.urovopaymentapp.domain.model.request.login

data class GrantingTicketRequest(
    val authentication: Authentication,
    val backendUserRequest: BackendUserRequest
){
    class Builder {
        private var authentication: Authentication? = null
        private var backendUserRequest: BackendUserRequest? = null

        fun setAuthentication(authentication: Authentication) = apply {
            this.authentication = authentication
        }

        fun setBackendUserRequest(backendUserRequest: BackendUserRequest) = apply {
            this.backendUserRequest = backendUserRequest
        }

        fun build(): GrantingTicketRequest {
            requireNotNull(authentication) { "Authentication must not be null" }
            requireNotNull(backendUserRequest) { "BackendUserRequest must not be null" }
            return GrantingTicketRequest(authentication!!, backendUserRequest!!)
        }
    }
}

