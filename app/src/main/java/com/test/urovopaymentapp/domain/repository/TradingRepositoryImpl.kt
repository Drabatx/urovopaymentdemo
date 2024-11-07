package com.test.urovopaymentapp.domain.repository

import android.content.Context
import com.test.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.test.urovopaymentapp.domain.model.TradingCardResponse
import com.test.urovopaymentapp.utils.UrovoResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TradingRepositoryImpl @Inject constructor(private val context: Context) : TradingRepository{

    override suspend fun startSale(posInputDatas: PosInputDatas):Flow<UrovoResult<TradingCardResponse>> = flow{
        emit(UrovoResult.Loading)
        delay(5000)
        val response = TradingCardResponse(
            result = true,
            message = "Operation successful."
        )
        emit(UrovoResult.Success(response))
    }
}