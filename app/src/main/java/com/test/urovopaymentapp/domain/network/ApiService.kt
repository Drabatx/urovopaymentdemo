package com.test.urovopaymentapp.domain.network

import com.test.urovopaymentapp.domain.model.TradingCardResponse
import com.test.urovopaymentapp.domain.model.request.login.GrantingTicketRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("TechArchitecture/mx/grantingTicket/V02")
    fun grantingTicket(@Body request: GrantingTicketRequest): Call<TradingCardResponse>
}