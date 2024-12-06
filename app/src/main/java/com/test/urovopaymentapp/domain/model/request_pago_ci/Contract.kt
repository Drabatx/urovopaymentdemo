package com.test.urovopaymentapp.domain.model.request_pago_ci


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contract(
    val channelContract: String="APIC",
    val chargingCurrency: ChargingCurrency,
    val contractNumber: String
) {
    // Builder para la clase Contract
    class Builder {
        private var channelContract: String = "APIC"
        private lateinit var chargingCurrency: ChargingCurrency
        private var contractNumber: String = ""

        fun setChannelContract(channelContract: String) = apply { this.channelContract = channelContract }
        fun setChargingCurrency(chargingCurrency: ChargingCurrency) = apply { this.chargingCurrency = chargingCurrency }
        fun setContractNumber(contractNumber: String) = apply { this.contractNumber = contractNumber }

        fun build(): Contract {
            // Validaciones opcionales antes de crear el objeto
            if (channelContract.isBlank()) throw IllegalArgumentException("ChannelContract is required")
            if (!::chargingCurrency.isInitialized) throw IllegalArgumentException("ChargingCurrency is required")
            if (contractNumber.isBlank()) throw IllegalArgumentException("ContractNumber is required")

            return Contract(
                channelContract = channelContract,
                chargingCurrency = chargingCurrency,
                contractNumber = contractNumber
            )
        }
    }
}
