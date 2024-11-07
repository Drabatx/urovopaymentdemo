package com.test.urovopaymentapp.domain.repository

import com.test.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.test.urovopaymentapp.domain.model.TradingCardResponse
import com.test.urovopaymentapp.utils.UrovoResult
import kotlinx.coroutines.flow.Flow

interface TradingRepository {
    suspend fun startSale(posInputDatas: PosInputDatas): Flow<UrovoResult<TradingCardResponse>>
}