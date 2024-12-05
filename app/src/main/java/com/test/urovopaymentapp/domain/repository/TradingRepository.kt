package com.test.urovopaymentapp.domain.repository

import com.test.urovopaymentapp.domain.model.TradingCardResponse
import com.test.urovopaymentapp.utils.Result
import kotlinx.coroutines.flow.Flow

interface TradingRepository {
    suspend fun loginToProcessPayments(): Flow<Result<TradingCardResponse>>
}