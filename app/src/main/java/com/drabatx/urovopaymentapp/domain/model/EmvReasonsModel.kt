package com.drabatx.urovopaymentapp.domain.model

enum class EmvReason {
    MESSAGE_REQUEST_ONLINE,
    REQUEST_ONLINEPIN,
    MESSAGE_ERROR,
    MESSAGE_MSR,
    MESSAGE_TIMEOUT,
    CANCEL_OPERATION,
    MESSAGE_APP_SELECT,
    MESSAGE_CARD_MESSAGE
}
data class EmvReasonsModel(val message: String, val reason: EmvReason) {

    // Clase Builder
    class Builder {
        private var message: String = ""
        private var reason: EmvReason? = null

        // Métodos para configurar los valores
        fun setMessage(message: String) = apply { this.message = message }
        fun setReason(reason: EmvReason) = apply { this.reason = reason }

        // Construye la instancia de EmvReasonsModel
        fun build(): EmvReasonsModel {
            requireNotNull(reason) { "reason no puede ser nulo" }
            return EmvReasonsModel(message, reason!!)
        }
    }
}