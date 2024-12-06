package com.test.urovopaymentapp.domain.network

import com.test.urovopaymentapp.domain.model.TradingCardResponse
import com.test.urovopaymentapp.domain.model.request.login.GrantingTicketRequest
import com.test.urovopaymentapp.domain.model.request_pago_ci.RequestPagoCi
import com.test.urovopaymentapp.domain.model.response_pago_ci.ResponsePagoCI
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("TechArchitecture/mx/grantingTicket/V02")
    fun grantingTicket(@Body request: GrantingTicketRequest): Call<TradingCardResponse>

    @Headers("Content-Type: application/json")
    @POST("servicePayments/V06/")
    fun paymentCI(
        @Header("tsec") tsec: String,
        @Body request: RequestPagoCi
    ): Call<ResponsePagoCI>
}