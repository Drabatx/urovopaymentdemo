package com.drabatx.urovopaymentapp.domain.repository;

enum class PinCodes(val code: Int, val message: String) {
    PIN_BLOCKED(7101, "El PIN está bloqueado"),
    LAST_CHANCE_PIN(7102, "Última oportunidad para ingresar el PIN"),
    TWO_CHANCES_PIN(7103, "Dos oportunidades para ingresar el PIN"),
    PIN_LENGTH_ERROR(7006, "Error en la longitud del PIN"),
    ANTI_BRUTE_FORCE_ERROR(7010, "Error de prevención de fuerza bruta"),
    WRONG_PIN(7016, "PIN incorrecto"),
    RETURN_CODE_ERROR(7071, "Código de retorno incorrecto"),
    IC_COMMAND_FAILED(7072, "Comando IC fallido"),
    CARD_DATA_ERROR(7073, "Error en los datos de la tarjeta"),
    PIN_BLOCKED_AGAIN(7074, "El PIN está bloqueado"),
    ENCRYPTION_ERROR(7075, "Error de encriptación"),

    // Offline PIN verification results
    OFFLINE_RETURN_CODE_ERROR(-198, "Código de retorno incorrecto en la verificación de PIN offline"),
    OFFLINE_IC_COMMAND_FAILED(-202, "Comando IC fallido en la verificación de PIN offline"),
    OFFLINE_PIN_BLOCKED(-192, "El PIN está bloqueado en la verificación de PIN offline"),
    USER_CANCEL_OR_TIMEOUT(-199, "El usuario canceló o tiempo de espera en el Pinpad"),
    BYPASS(1, "Se omitió la verificación de PIN"),
    SUCCESS(0, "Verificación de PIN exitosa");
    companion object {
        /**
         * Function to retrieve an [PinCodes] by its code.
         */
        fun fromCode(code: Int): PinCodes? {
            return values().find { it.code == code }
        }
    }
}