package com.test.urovopaymentapp.domain.repository

import com.test.urovopaymentapp.domain.model.TradingCardResponse
import com.test.urovopaymentapp.domain.model.request.login.GrantingTicketRequest
import com.test.urovopaymentapp.domain.model.request_pago_ci.RequestPagoCi
import com.test.urovopaymentapp.domain.model.response_pago_ci.ResponsePagoCI
import com.test.urovopaymentapp.utils.Result
import kotlinx.coroutines.flow.Flow

interface TradingRepository {
    suspend fun loginToProcessPayments(request: GrantingTicketRequest): Flow<Result<TradingCardResponse>>
    suspend fun processPaymentCI(tsec: String, request: RequestPagoCi): Flow<Result<ResponsePagoCI>>
}