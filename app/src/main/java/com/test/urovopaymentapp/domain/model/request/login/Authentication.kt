package com.test.urovopaymentapp.domain.model.request.login

data class Authentication(
    val authenticationData: List<AuthenticationData>,
    val authenticationType: String,
    val consumerID: String,
    val userID: String
) {
    class Builder {
        private var authenticationData: List<AuthenticationData> = emptyList()
        private var authenticationType: String = ""
        private var consumerID: String = ""
        private var userID: String = ""

        fun setAuthenticationData(authenticationData: List<AuthenticationData>) = apply {
            this.authenticationData = authenticationData
        }

        fun setAuthenticationType(authenticationType: String) = apply {
            this.authenticationType = authenticationType
        }

        fun setConsumerID(consumerID: String) = apply {
            this.consumerID = consumerID
        }

        fun setUserID(userID: String) = apply {
            this.userID = userID
        }

        fun build(): Authentication {
            require(authenticationData.isNotEmpty()) { "AuthenticationData must not be empty" }
            require(authenticationType.isNotBlank()) { "AuthenticationType must not be blank" }
            require(consumerID.isNotBlank()) { "ConsumerID must not be blank" }
            require(userID.isNotBlank()) { "UserID must not be blank" }
            return Authentication(authenticationData, authenticationType, consumerID, userID)
        }
    }
}
