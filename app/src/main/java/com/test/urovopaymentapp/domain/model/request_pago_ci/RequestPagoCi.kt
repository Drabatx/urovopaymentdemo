package com.test.urovopaymentapp.domain.model.request_pago_ci


import kotlinx.serialization.Serializable

@Serializable
data class RequestPagoCi(
    val sender: Sender,
    val taxableCash: TaxableCash,
    val numberAgreement: String,
    val reference: String,
    val concept: String="CONCEPTO CIE"
) {
    class Builder {
        private var concept: String = ""
        private var numberAgreement: String = ""
        private var reference: String = ""
        private lateinit var sender: Sender
        private lateinit var taxableCash: TaxableCash

        fun setConcept(concept: String) = apply { this.concept = concept }
        fun setNumberAgreement(numberAgreement: String) =
            apply { this.numberAgreement = numberAgreement }

        fun setReference(reference: String) = apply { this.reference = reference }
        fun setSender(sender: Sender) = apply { this.sender = sender }
        fun setTaxableCash(taxableCash: TaxableCash) = apply { this.taxableCash = taxableCash }

        fun build(): RequestPagoCi {
            // Validaciones opcionales antes de crear el objeto
            if (!::sender.isInitialized) throw IllegalArgumentException("Sender is required")
            if (!::taxableCash.isInitialized) throw IllegalArgumentException("TaxableCash is required")

            return RequestPagoCi(
                concept = concept,
                numberAgreement = numberAgreement,
                reference = reference,
                sender = sender,
                taxableCash = taxableCash
            )
        }
    }
}