package com.test.urovopaymentapp.domain.model.request_pago_ci


import kotlinx.serialization.Serializable

@Serializable
data class OperationType(
    val id: String = "PAG"
) {
    class Builder {
        private var id: String = "PAG"

        fun setId(id: String) = apply { this.id = id }

        fun build(): OperationType {
            return OperationType(id)
        }
    }
}