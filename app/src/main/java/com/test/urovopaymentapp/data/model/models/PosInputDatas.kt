package com.test.urovopaymentapp.data.model.pos2.models

import com.google.gson.Gson
import com.test.urovopaymentapp.data.model.models.StIso8583
import com.test.urovopaymentapp.data.model.db.TranslogModel
import java.io.Serializable

/**
 * pos passed in parameter class
 *
 * @author Administrator
 */
class PosInputDatas private constructor(
    /**
     * Número de empleado.
     */
    var userNo: String = "",

    /**
     * ID de la organización.
     */
    var nodeID: String = "",

    /**
     * Número de serie del PDA.
     */
    var devNo: String = "",

    /**
     * Monto de la transacción.
     */
    var amt: String = "",

    /**
     * Contraseña del titular de la tarjeta.
     */
    var szPINData: String = "",

    /**
     * Número de comprobante.
     */
    var pzNumber: String = "",

    /**
     * Número de lote.
     */
    var bacthNo: String = "",

    /**
     * Número de cuenta.
     */
    var pan: String = "",

    /**
     * Pista magnética dos.
     */
    var track2: String = "",

    /**
     * Pista magnética tres.
     */
    var track3: String = "",

    /**
     * Periodo de validez de la tarjeta.
     */
    var szExpDate: String = "",

    /**
     * Número de serie de la tarjeta.
     */
    var szCardSeqNo: String = "",

    /**
     * Tipo de contraseña de la tarjeta.
     */
    var pinPad: String = "",

    /**
     * Número de guía o número de pedido.
     */
    private var sWayNumber: String = "",

    /**
     * Indica si se insertó, deslizó o pasó la tarjeta.
     */
    var swipedMode: Int = 0,

    /**
     * Tipo de transacción actual.
     */
    var iTransNo: Int = 0,

    /**
     * Número de referencia.
     */
    var refString: String = "",

    /**
     * Tiempo de la transacción.
     */
    var date: String? = null,

    /**
     * Tiempo de fraccionamiento.
     */
    var stageTime: String? = null,

    /**
     * Campo del dominio 55.
     */
    var file55: String? = null,

    /**
     * ID de índice.
     */
    var indexId: Int = 0,

    /**
     * Hora de la transacción.
     */
    var time: String? = null,

    /**
     * Firma del cliente.
     */
    var qianMing: String? = null,

    /**
     * Modelo de transacción (Swipe a card to get a gift).
     */
    var translogModel: TranslogModel? = null,

    /**
     * Tipo de transacción de Yihi o número de tarjeta de transferencia.
     */
    var yhTransNo: String? = null,

    /**
     * Volumen de petróleo o información de la pista 2 de la tarjeta de transferencia.
     */
    var oilMass: String? = null,

    /**
     * Kilometraje o contraseña de la tarjeta de transferencia.
     */
    var mileage: String? = null,

    /**
     * Indica si se insertó, deslizó o pasó la tarjeta (carga no especificada).
     */
    var swipedModeTwo: Byte = 0,

    /**
     * Monto del descuento del cupón.
     */
    var qbpocAmt: String? = null,

    /**
     * Monto a pagar con el cupón.
     */
    var qbpocCardamt: String? = null,

    /**
     * Detalles del descuento.
     */
    var qbpocInt: Int = 0,

    /**
     * Mensaje de detalles del descuento.
     */
    var qbpocmessage: String? = null,

    /**
     * Indica si es un consumo preferencial.
     */
    var mark: Int = 0,

    /**
     * Modo de caracteres de la tarifa de cuotas.
     */
    var install: String? = null,

    /**
     * Indica si es un taxi.
     */
    var isCzc: Boolean = false,

    /**
     * Número interno del comerciante.
     */
    var internal: String? = null,

    /**
     * Saldo de efectivo electrónico.
     */
    var eCBlance: String? = null,

    /**
     * Número de intentos de ingresar la contraseña.
     */
    var pwdCount: String? = null,

    /**
     * Valor de retorno de CVM.
     */
    var cvmStartRet: String? = null,

    /**
     * PIN offline.
     */
    var offlinePin: String? = null,

    /**
     * Indica si el PIN es online.
     */
    var isOnlinePin: Boolean = true,

    /**
     * Campo de observación 1.
     */
    var remark1: String? = null,

    /**
     * Campo de observación 2.
     */
    var remark2: String? = null,

    /**
     * Campo de observación 3.
     */
    var remark3: String? = null,

    /**
     * Campo de observación 4.
     */
    var remark4: String? = null,

    /**
     * Monto del pedido.
     */
    var orderAmount: Int = 0,

    /**
     * Número de pedido de UnionPay.
     */
    var transNumber: String? = null,

    /**
     * Número de comerciante de UnionPay.
     */
    var merID: String? = null,

    /**
     * Tiempo del pedido.
     */
    var orderTime: String? = null,

    /**
     * Número de pedido.
     */
    var orderNumber: String? = null,

    /**
     * Mapa para almacenar campos personalizados.
     */
    var map: HashMap<String, Any>? = null,

    var stIso8583: StIso8583? = null
) : Serializable {

    fun update(
        userNo: String? = this.userNo,
        nodeID: String? = this.nodeID,
        devNo: String? = this.devNo,
        amt: String? = this.amt,
        szPINData: String? = this.szPINData,
        pzNumber: String? = this.pzNumber,
        bacthNo: String? = this.bacthNo,
        pan: String? = this.pan,
        track2: String? = this.track2,
        track3: String? = this.track3,
        szExpDate: String? = this.szExpDate,
        szCardSeqNo: String? = this.szCardSeqNo,
        pinPad: String? = this.pinPad,
        swipedMode: Int? = this.swipedMode,
        refString: String? = this.refString,
        date: String? = this.date,
        stageTime: String? = this.stageTime,
        file55: String? = this.file55,
        indexId: Int? = this.indexId,
        time: String? = this.time,
        qianMing: String? = this.qianMing,
        translogModel: TranslogModel? = this.translogModel,
        yhTransNo: String? = this.yhTransNo,
        oilMass: String? = this.oilMass,
        mileage: String? = this.mileage,
        swipedModeTwo: Byte? = this.swipedModeTwo,
        qbpocAmt: String? = this.qbpocAmt,
        qbpocCardamt: String? = this.qbpocCardamt,
        qbpocInt: Int? = this.qbpocInt,
        qbpocmessage: String? = this.qbpocmessage,
        mark: Int? = this.mark,
        install: String? = this.install,
        isCzc: Boolean? = this.isCzc,
        internal: String? = this.internal,
        eCBlance: String? = this.eCBlance,
        pwdCount: String? = this.pwdCount,
        cvmStartRet: String? = this.cvmStartRet,
        offlinePin: String? = this.offlinePin,
        isOnlinePin: Boolean? = this.isOnlinePin,
        remark1: String? = this.remark1,
        remark2: String? = this.remark2,
        remark3: String? = this.remark3,
        remark4: String? = this.remark4,
        orderAmount: Int? = this.orderAmount,
        transNumber: String? = this.transNumber,
        merID: String? = this.merID,
        orderTime: String? = this.orderTime,
        orderNumber: String? = this.orderNumber,
        map: HashMap<String, Any>? = this.map,
        stIso8583: StIso8583? = this.stIso8583
    ): PosInputDatas {
        return PosInputDatas(
            userNo = userNo ?: this.userNo,
            nodeID = nodeID ?: this.nodeID,
            devNo = devNo ?: this.devNo,
            amt = amt ?: this.amt,
            szPINData = szPINData ?: this.szPINData,
            pzNumber = pzNumber ?: this.pzNumber,
            bacthNo = bacthNo ?: this.bacthNo,
            pan = pan ?: this.pan,
            track2 = track2 ?: this.track2,
            track3 = track3 ?: this.track3,
            szExpDate = szExpDate ?: this.szExpDate,
            szCardSeqNo = szCardSeqNo ?: this.szCardSeqNo,
            pinPad = pinPad ?: this.pinPad,
            swipedMode = swipedMode ?: this.swipedMode,
            refString = refString ?: this.refString,
            date = date ?: this.date,
            stageTime = stageTime ?: this.stageTime,
            file55 = file55 ?: this.file55,
            indexId = indexId ?: this.indexId,
            time = time ?: this.time,
            qianMing = qianMing ?: this.qianMing,
            translogModel = translogModel ?: this.translogModel,
            yhTransNo = yhTransNo ?: this.yhTransNo,
            oilMass = oilMass ?: this.oilMass,
            mileage = mileage ?: this.mileage,
            swipedModeTwo = swipedModeTwo ?: this.swipedModeTwo,
            qbpocAmt = qbpocAmt ?: this.qbpocAmt,
            qbpocCardamt = qbpocCardamt ?: this.qbpocCardamt,
            qbpocInt = qbpocInt ?: this.qbpocInt,
            qbpocmessage = qbpocmessage ?: this.qbpocmessage,
            mark = mark ?: this.mark,
            install = install ?: this.install,
            isCzc = isCzc ?: this.isCzc,
            internal = internal ?: this.internal,
            eCBlance = eCBlance ?: this.eCBlance,
            pwdCount = pwdCount ?: this.pwdCount,
            cvmStartRet = cvmStartRet ?: this.cvmStartRet,
            offlinePin = offlinePin ?: this.offlinePin,
            isOnlinePin = isOnlinePin ?: this.isOnlinePin,
            remark1 = remark1 ?: this.remark1,
            remark2 = remark2 ?: this.remark2,
            remark3 = remark3 ?: this.remark3,
            remark4 = remark4 ?: this.remark4,
            orderAmount = orderAmount ?: this.orderAmount,
            transNumber = transNumber ?: this.transNumber,
            merID = merID ?: this.merID,
            orderTime = orderTime ?: this.orderTime,
            orderNumber = orderNumber ?: this.orderNumber,
            map = map ?: this.map,
            stIso8583 = stIso8583 ?: this.stIso8583
        )
    }

    class Builder {
        private var userNo: String = ""
        private var nodeID: String = ""
        private var devNo: String = ""
        private var amt: String = ""
        private var szPINData: String = ""
        private var pzNumber: String = ""
        private var bacthNo: String = ""
        private var pan: String = ""
        private var track2: String = ""
        private var track3: String = ""
        private var szExpDate: String = ""
        private var szCardSeqNo: String = ""
        private var pinPad: String = ""
        private var sWayNumber: String = ""
        private var swipedMode: Int = 0
        private var iTransNo: Int = 0
        private var refString: String = ""
        private var date: String? = null
        private var stageTime: String? = null
        private var file55: String? = null
        private var indexId: Int = 0
        private var time: String? = null
        private var qianMing: String? = null
        private var translogModel: TranslogModel? = null
        private var yhTransNo: String? = null
        private var oilMass: String? = null
        private var mileage: String? = null
        private var swipedModeTwo: Byte = 0
        private var qbpocAmt: String? = null
        private var qbpocCardamt: String? = null
        private var qbpocInt: Int = 0
        private var qbpocmessage: String? = null
        private var mark: Int = 0
        private var install: String? = null
        private var isCzc: Boolean = false
        private var internal: String? = null
        private var eCBlance: String? = null
        private var pwdCount: String? = null
        private var cvmStartRet: String? = null
        private var offlinePin: String? = null
        private var isOnlinePin: Boolean = true
        private var remark1: String? = null
        private var remark2: String? = null
        private var remark3: String? = null
        private var remark4: String? = null
        private var orderAmount: Int = 0
        private var transNumber: String? = null
        private var merID: String? = null
        private var orderTime: String? = null
        private var orderNumber: String? = null
        private var map: HashMap<String, Any>? = null
        private var stIso8583: StIso8583? = null

        fun setUserNo(userNo: String) = apply { this.userNo = userNo }
        fun setNodeID(nodeID: String) = apply { this.nodeID = nodeID }
        fun setDevNo(devNo: String) = apply { this.devNo = devNo }
        fun setAmt(amt: String) = apply { this.amt = amt }
        fun setSzPINData(szPINData: String) = apply { this.szPINData = szPINData }
        fun setPzNumber(pzNumber: String) = apply { this.pzNumber = pzNumber }
        fun setBacthNo(bacthNo: String) = apply { this.bacthNo = bacthNo }
        fun setPan(pan: String) = apply { this.pan = pan }
        fun setTrack2(track2: String) = apply { this.track2 = track2 }
        fun setTrack3(track3: String) = apply { this.track3 = track3 }
        fun setSzExpDate(szExpDate: String) = apply { this.szExpDate = szExpDate }
        fun setSzCardSeqNo(szCardSeqNo: String) = apply { this.szCardSeqNo = szCardSeqNo }
        fun setPinPad(pinPad: String) = apply { this.pinPad = pinPad }
        fun setSWayNumber(sWayNumber: String) = apply { this.sWayNumber = sWayNumber }
        fun setSwipedMode(swipedMode: Int) = apply { this.swipedMode = swipedMode }
        fun setITransNo(iTransNo: Int) = apply { this.iTransNo = iTransNo }
        fun setRefString(refString: String) = apply { this.refString = refString }
        fun setDate(date: String?) = apply { this.date = date }
        fun setStageTime(stageTime: String?) = apply { this.stageTime = stageTime }
        fun setFile55(file55: String?) = apply { this.file55 = file55 }
        fun setIndexId(indexId: Int) = apply { this.indexId = indexId }
        fun setTime(time: String?) = apply { this.time = time }
        fun setQianMing(qianMing: String?) = apply { this.qianMing = qianMing }
        fun setTranslogModel(translogModel: TranslogModel?) =
            apply { this.translogModel = translogModel }

        fun setYhTransNo(yhTransNo: String?) = apply { this.yhTransNo = yhTransNo }
        fun setOilMass(oilMass: String?) = apply { this.oilMass = oilMass }
        fun setMileage(mileage: String?) = apply { this.mileage = mileage }
        fun setSwipedModeTwo(swipedModeTwo: Byte) = apply { this.swipedModeTwo = swipedModeTwo }
        fun setQbpocAmt(qbpocAmt: String?) = apply { this.qbpocAmt = qbpocAmt }
        fun setQbpocCardamt(qbpocCardamt: String?) = apply { this.qbpocCardamt = qbpocCardamt }
        fun setQbpocInt(qbpocInt: Int) = apply { this.qbpocInt = qbpocInt }
        fun setQbpocmessage(qbpocmessage: String?) = apply { this.qbpocmessage = qbpocmessage }
        fun setMark(mark: Int) = apply { this.mark = mark }
        fun setInstall(install: String?) = apply { this.install = install }
        fun setIsCzc(isCzc: Boolean) = apply { this.isCzc = isCzc }
        fun setInternal(internal: String?) = apply { this.internal = internal }
        fun setECBlance(eCBlance: String?) = apply { this.eCBlance = eCBlance }
        fun setPwdCount(pwdCount: String?) = apply { this.pwdCount = pwdCount }
        fun setCvmStartRet(cvmStartRet: String?) = apply { this.cvmStartRet = cvmStartRet }
        fun setOfflinePin(offlinePin: String?) = apply { this.offlinePin = offlinePin }
        fun setIsOnlinePin(isOnlinePin: Boolean) = apply { this.isOnlinePin = isOnlinePin }
        fun setRemark1(remark1: String?) = apply { this.remark1 = remark1 }
        fun setRemark2(remark2: String?) = apply { this.remark2 = remark2 }
        fun setRemark3(remark3: String?) = apply { this.remark3 = remark3 }
        fun setRemark4(remark4: String?) = apply { this.remark4 = remark4 }
        fun setOrderAmount(orderAmount: Int) = apply { this.orderAmount = orderAmount }
        fun setTransNumber(transNumber: String?) = apply { this.transNumber = transNumber }
        fun setMerID(merID: String?) = apply { this.merID = merID }
        fun setOrderTime(orderTime: String?) = apply { this.orderTime = orderTime }
        fun setOrderNumber(orderNumber: String?) = apply { this.orderNumber = orderNumber }
        fun setMap(map: HashMap<String, Any>?) = apply { this.map = map }
        fun setStIso8583(stIso8583: StIso8583?) = apply { this.stIso8583 = stIso8583 }
        fun build() = PosInputDatas(
            userNo,
            nodeID,
            devNo,
            amt,
            szPINData,
            pzNumber,
            bacthNo,
            pan,
            track2,
            track3,
            szExpDate,
            szCardSeqNo,
            pinPad,
            sWayNumber,
            swipedMode,
            iTransNo,
            refString,
            date,
            stageTime,
            file55,
            indexId,
            time,
            qianMing,
            translogModel,
            yhTransNo,
            oilMass,
            mileage,
            swipedModeTwo,
            qbpocAmt,
            qbpocCardamt,
            qbpocInt,
            qbpocmessage,
            mark,
            install,
            isCzc,
            internal,
            eCBlance,
            pwdCount,
            cvmStartRet,
            offlinePin,
            isOnlinePin,
            remark1,
            remark2,
            remark3,
            remark4,
            orderAmount,
            transNumber,
            merID,
            orderTime,
            orderNumber,
            map, stIso8583
        )
    }
}

fun PosInputDatas.toJson(): String {
    val gson = Gson()
    return gson.toJson(this)
}

fun String.toPosInputDatas(): PosInputDatas {
    val gson = Gson()
    return gson.fromJson(this, PosInputDatas::class.java)
}
