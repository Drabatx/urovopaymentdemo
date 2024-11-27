package com.test.urovopaymentapp.domain.model.request.login

data class AuthenticationData(
    val authenticationData: List<String>,
    val idAuthenticationData: String
) {
    class Builder {
        private var authenticationData: List<String> = emptyList()
        private var idAuthenticationData: String = ""

        fun setAuthenticationData(authenticationData: List<String>) = apply {
            this.authenticationData = authenticationData
        }

        fun setIdAuthenticationData(idAuthenticationData: String) = apply {
            this.idAuthenticationData = idAuthenticationData
        }

        fun build(): AuthenticationData {
            require(authenticationData.isNotEmpty()) { "AuthenticationData must not be empty" }
            require(idAuthenticationData.isNotBlank()) { "IdAuthenticationData must not be blank" }
            return AuthenticationData(authenticationData, idAuthenticationData)
        }
    }
}