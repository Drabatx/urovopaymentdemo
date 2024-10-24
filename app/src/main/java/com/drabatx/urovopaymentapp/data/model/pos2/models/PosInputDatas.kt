package com.drabatx.urovopaymentapp.data.model.pos2.models

import com.drabatx.urovopaymentapp.data.model.pos2.db.TranslogModel
import java.io.Serializable

/**
 * pos passed in parameter class
 *
 * @author Administrator
 */
class PosInputDatas : Serializable {
    /**
     * Número de empleado.
     */
    var userNo: String = ""

    /**
     * ID de la organización.
     */
    var nodeID: String = ""

    /**
     * Número de serie del PDA.
     */
    var devNo: String = ""

    /**
     * Monto de la transacción.
     */
    var amt: String = ""

    /**
     * Contraseña del titular de la tarjeta.
     */
    var szPINData: String = ""

    /**
     * Número de comprobante.
     */
    var pzNumber: String = ""

    /**
     * Número de lote.
     */
    var bacthNo: String = ""

    /**
     * Número de cuenta.
     */
    var pan: String = ""

    /**
     * Pista magnética dos.
     */
    var track2: String = ""

    /**
     * Pista magnética tres.
     */
    var track3: String = ""

    /**
     * Periodo de validez de la tarjeta.
     */
    var szExpDate: String = ""

    /**
     * Número de serie de la tarjeta.
     */
    var szCardSeqNo: String = ""

    /**
     * Tipo de contraseña de la tarjeta.
     */
    var pinPad: String = ""

    /**
     * Número de guía o número de pedido.
     */
    private var sWayNumber = ""

    /**
     * Indica si se insertó, deslizó o pasó la tarjeta.
     */
    var swipedMode: Int = 0

    /**
     * Tipo de transacción actual.
     */
    private var iTransNo = 0

    /**
     * Número de referencia.
     */
    var refString: String = ""

    /**
     * Tiempo de la transacción.
     */
    var date: String? = null

    /**
     * Tiempo de fraccionamiento.
     */
    var stageTime: String? = null

    /**
     * Campo del dominio 55.
     */
    var file55: String? = null

    /**
     * ID de índice.
     */
    var indexId: Int = 0

    /**
     * Hora de la transacción.
     */
    var time: String? = null

    /**
     * Firma del cliente.
     */
    var qianMing: String? = null

    /**
     * Modelo de transacción (Swipe a card to get a gift).
     */
    var translogModel: TranslogModel? = null

    /**
     * Tipo de transacción de Yihi o número de tarjeta de transferencia.
     */
    var yhTransNo: String? = null

    /**
     * Volumen de petróleo o información de la pista 2 de la tarjeta de transferencia.
     */
    var oilMass: String? = null

    /**
     * Kilometraje o contraseña de la tarjeta de transferencia.
     */
    var mileage: String? = null

    /**
     * Indica si se insertó, deslizó o pasó la tarjeta (carga no especificada).
     */
    var swipedModeTwo: Byte = 0

    /**
     * Monto del descuento del cupón.
     */
    var qbpocAmt: String? = null

    /**
     * Monto a pagar con el cupón.
     */
    var qbpocCardamt: String? = null

    /**
     * Detalles del descuento.
     */
    var qbpocInt: Int = 0

    /**
     * Mensaje de detalles del descuento.
     */
    var qbpocmessage: String? = null

    /**
     * Indica si es un consumo preferencial.
     */
    var mark: Int = 0

    /**
     * Modo de caracteres de la tarifa de cuotas.
     */
    var install: String? = null

    /**
     * Indica si es un taxi.
     */
    var isCzc: Boolean = false

    /**
     * Número interno del comerciante.
     */
    var internal: String? = null

    /**
     * Saldo de efectivo electrónico.
     */
    var eCBlance: String? = null

    /**
     * Número de intentos de ingresar la contraseña.
     */
    var pwdCount: String? = null

    /**
     * Valor de retorno de CVM.
     */
    var cvmStartRet: String? = null

    /**
     * PIN offline.
     */
    var offlinePin: String? = null

    /**
     * Indica si el PIN es online.
     */
    var isOnlinePin: Boolean = true

    /**
     * Campo de observación 1.
     */
    var remark1: String? = null

    /**
     * Campo de observación 2.
     */
    var remark2: String? = null

    /**
     * Campo de observación 3.
     */
    var remark3: String? = null

    /**
     * Campo de observación 4.
     */
    var remark4: String? = null

    /**
     * Monto del pedido.
     */
    var orderAmount: Int = 0

    /**
     * Número de pedido de UnionPay.
     */
    var transNumber: String? = null

    /**
     * Número de comerciante de UnionPay.
     */
    var merID: String? = null

    /**
     * Tiempo del pedido.
     */
    var orderTime: String? = null

    /**
     * Número de pedido.
     */
    var orderNumber: String? = null

    /**
     * Mapa para almacenar campos personalizados.
     */
    var map: HashMap<String, Any>? = null

    /** Establece si el PIN es online. */
    fun setIsOnlinePin(isOnlinePin: Boolean) {
        this.isOnlinePin = isOnlinePin
    }

    /** Obtiene el número de guía o número de pedido. */
    fun getsWayNumber(): String {
        return sWayNumber
    }

    /** Establece el número de guía o número de pedido. */
    fun setsWayNumber(sWayNumber: String) {
        this.sWayNumber = sWayNumber
    }

    /** Obtiene el tipo de transacción actual. */
    fun getiTransNo(): Int {
        return iTransNo
    }

    /** Establece el tipo de transacción actual. */
    fun setiTransNo(iTransNo: Int) {
        this.iTransNo = iTransNo
    }

    companion object {
        const val serialversionuid: Long = 1L
    }
}
