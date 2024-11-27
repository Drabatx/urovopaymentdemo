package com.test.urovopaymentapp.domain.model.request.login

data class BackendUserRequest(
    val accessCode: String,
    val dialogId: String,
    val userId: String
) {
    class Builder {
        private var accessCode: String = ""
        private var dialogId: String = ""
        private var userId: String = ""

        fun setAccessCode(accessCode: String) = apply {
            this.accessCode = accessCode
        }

        fun setDialogId(dialogId: String) = apply {
            this.dialogId = dialogId
        }

        fun setUserId(userId: String) = apply {
            this.userId = userId
        }

        fun build(): BackendUserRequest {
            require(accessCode.isNotBlank()) { "AccessCode must not be blank" }
            return BackendUserRequest(accessCode, dialogId, userId)
        }
    }
}