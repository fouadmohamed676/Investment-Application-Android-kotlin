package com.example.project.network

import com.example.project.model.banners.ResponseBanners
import com.example.project.model.invesment.InvesmentResponse
import com.example.project.model.invesment.op.ResponseInvestmentOperations
import com.example.project.model.invesment.request.RequestInvestment
import com.example.project.model.invesment.respones.ResponseInvestment
import com.example.project.model.login.request.LoginRequest
import com.example.project.model.login.response.ResponseLogin
import com.example.project.model.register.RegisterModel
import com.example.project.model.register.response.RegisterResponse
 import com.example.project.model.user.accountBank.ResponseAccountBank
import com.example.project.model.user.serviceById.ResponseServiceId
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkApi {


    @GET("service/{id}") // all doctors in this service
    suspend fun getServiceUser(@Path("id") service_id: Int): Response<ResponseServiceId>

    @GET("user/bank/{id}") // all Patient Orders
    suspend fun getAccounts(@Path("id") user_id: Int): Response<ResponseAccountBank>

    @GET("user/investment_operations/{id}") // all Patient Orders
    suspend fun getInvestmentUser(@Path("id") user_id: Int): Response<ResponseInvestmentOperations>

    @POST("user/app/register")  // register Patient
    suspend fun register(@Body data: RegisterModel): Response<RegisterResponse>
//    git@github.com:fouadmohamed676/Investment-Application-Android-kotlin.git
    @POST("user/app/login")  // login
    suspend fun login(@Body data: LoginRequest): Response<ResponseLogin>

    @POST("user/create_investment_operations")  // login
    suspend fun create(@Body data: RequestInvestment): Response<ResponseInvestment>

    @GET("user/banners")    //Banners
    suspend fun banners(): Response<ResponseBanners>

    @GET("user/investments")    //Banners
    suspend fun investments(): Response<InvesmentResponse>


}