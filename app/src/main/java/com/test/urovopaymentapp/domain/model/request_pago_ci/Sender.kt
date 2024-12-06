package com.test.urovopaymentapp.domain.model.request_pago_ci


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sender(
    val operationType: OperationType=OperationType(),
    val contract: Contract,
    val serviceNumber: String,
    val terminalId: String,
    val chargeAccount: String
){
    class Builder {
        private var operationType: OperationType = OperationType()
        private lateinit var contract: Contract
        private var serviceNumber: String = ""
        private var terminalId: String = ""
        private var chargeAccount: String = ""

        fun setOperationType(operationType: OperationType) = apply { this.operationType = operationType }
        fun setContract(contract: Contract) = apply { this.contract = contract }
        fun setServiceNumber(serviceNumber: String) = apply { this.serviceNumber = serviceNumber }
        fun setTerminalId(terminalId: String) = apply { this.terminalId = terminalId }
        fun setChargeAccount(chargeAccount: String) = apply { this.chargeAccount = chargeAccount }

        fun build(): Sender {
            // Validaciones antes de construir
            if (!::contract.isInitialized) throw IllegalArgumentException("Contract is required")
            if (serviceNumber.isBlank()) throw IllegalArgumentException("ServiceNumber is required")
            if (terminalId.isBlank()) throw IllegalArgumentException("TerminalId is required")
            if (chargeAccount.isBlank()) throw IllegalArgumentException("ChargeAccount is required")

            return Sender(
                operationType = operationType,
                contract = contract,
                serviceNumber = serviceNumber,
                terminalId = terminalId,
                chargeAccount = chargeAccount
            )
        }
    }
}