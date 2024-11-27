package com.test.urovopaymentapp.domain.repository

import android.content.Context
import com.test.urovopaymentapp.data.local.preferences.PreferencesConfigs
import com.test.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.test.urovopaymentapp.domain.model.TradingCardResponse
import com.test.urovopaymentapp.domain.model.request.login.GrantingTicketRequest
import com.test.urovopaymentapp.domain.network.ApiService
import com.test.urovopaymentapp.utils.UrovoResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TradingRepositoryImpl @Inject constructor(
    private val context: Context,
    private val apiService: ApiService
) : TradingRepository {

    override suspend fun loginToProcessPayments(): Flow<UrovoResult<TradingCardResponse>> =
        flow {
            val request = GrantingTicketRequest.Builder().build()
            val responseFlow =
                MutableStateFlow<UrovoResult<TradingCardResponse>>(UrovoResult.Loading)

            apiService.grantingTicket(request = request)
                .enqueue(object : Callback<TradingCardResponse> {
                    override fun onResponse(
                        call: Call<TradingCardResponse>,
                        response: Response<TradingCardResponse>
                    ) {
                        if (response.isSuccessful) {
                            val tsec = response.headers()["tsec"]
                            val consumerRequestID = response.headers()["ConsumerRequestID"]

                            PreferencesConfigs.tsecHeader = tsec ?: ""
                            PreferencesConfigs.customerIdHeader = consumerRequestID ?: ""
                            PreferencesConfigs.timestampHeader = System.currentTimeMillis()
                            response.body()?.let {
                                responseFlow.value = UrovoResult.Success(it)
                            }
                        } else {
                            responseFlow.value = UrovoResult.Error(Exception("Error al autenticarse"))
                        }
                    }

                    override fun onFailure(call: Call<TradingCardResponse>, t: Throwable) {
                        responseFlow.value = UrovoResult.Error(t)
                    }
                })

            emitAll(responseFlow)
        }
}