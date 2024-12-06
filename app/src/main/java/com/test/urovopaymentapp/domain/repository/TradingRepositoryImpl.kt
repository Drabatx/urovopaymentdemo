package com.test.urovopaymentapp.domain.repository

import com.test.urovopaymentapp.data.local.preferences.PreferencesConfigs
import com.test.urovopaymentapp.domain.model.TradingCardResponse
import com.test.urovopaymentapp.domain.model.request.login.GrantingTicketRequest
import com.test.urovopaymentapp.domain.model.request_pago_ci.RequestPagoCi
import com.test.urovopaymentapp.domain.model.response_pago_ci.ResponsePagoCI
import com.test.urovopaymentapp.domain.network.ApiService
import com.test.urovopaymentapp.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TradingRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TradingRepository {

    override suspend fun loginToProcessPayments(request: GrantingTicketRequest): Flow<Result<TradingCardResponse>> =
        flow {
            try {
                val responseFlow = MutableStateFlow<Result<TradingCardResponse>>(Result.Loading())
                apiService.grantingTicket(request = request)
                    .enqueue(object : Callback<TradingCardResponse> {
                        override fun onResponse(
                            call: Call<TradingCardResponse>,
                            response: Response<TradingCardResponse>
                        ) {
                            if (response.isSuccessful) {
                                val tsec = response.headers()["tsec"]
                                val consumerRequestID = response.headers()["ConsumerRequestID"]
                                if (tsec != null && consumerRequestID != null) {
                                    PreferencesConfigs.tsecHeader = tsec
                                    PreferencesConfigs.customerIdHeader = consumerRequestID
                                    PreferencesConfigs.timestampHeader = System.currentTimeMillis()
                                    response.body()?.let {
                                        responseFlow.value = Result.Success(it)
                                    }
                                } else {
                                    responseFlow.value =
                                        Result.Error(Exception("Error al autenticarse"))
                                }

                            } else {
                                responseFlow.value =
                                    Result.Error(Exception("Error al autenticarse"))
                            }
                        }

                        override fun onFailure(call: Call<TradingCardResponse>, t: Throwable) {
                            responseFlow.value = Result.Error(t)
                        }
                    })

                emitAll(responseFlow)
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }

    override suspend fun processPaymentCI(
        tsec: String,
        request: RequestPagoCi
    ): Flow<Result<ResponsePagoCI>> =
        flow {
            try {
                val responseFlow = MutableStateFlow<Result<ResponsePagoCI>>(Result.Loading())
                apiService.paymentCI(tsec, request).enqueue(object : Callback<ResponsePagoCI> {
                    override fun onResponse(
                        call: Call<ResponsePagoCI>,
                        response: Response<ResponsePagoCI>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                responseFlow.value = Result.Success(it)
                            }
                        } else {
                            responseFlow.value =
                                Result.Error(Exception("Error al Procesar el pago"))
                        }
                    }

                    override fun onFailure(call: Call<ResponsePagoCI>, t: Throwable) {
                        responseFlow.value = Result.Error(t)
                    }
                })
                emitAll(responseFlow)
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}