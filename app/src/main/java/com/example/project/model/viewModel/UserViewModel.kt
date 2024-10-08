package com.example.project.model.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.model.login.request.LoginRequest
import com.example.project.model.register.RegisterModel
import com.example.project.model.register.response.Response
import com.example.project.network.RetrofitInstance
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val user: MutableLiveData<List<Response>> = MutableLiveData()
    val userData: LiveData<List<Response>>
        get() = user
    private val userBank: MutableLiveData<List<com.example.project.model.user.accountBank.Response>> = MutableLiveData()
    val responseUserBank: LiveData<List<com.example.project.model.user.accountBank.Response>>
        get() = userBank
    private val _responseLogin: MutableLiveData<List<com.example.project.model.login.response.Response>> =
        MutableLiveData()
    val getResponseLogin: LiveData<List<com.example.project.model.login.response.Response>>
        get() = _responseLogin

    fun loginUser(data: LoginRequest) {

        viewModelScope.launch {
            val data = RetrofitInstance.api.login(data)
            Log.e("message : ", data.message().toString())
            Log.e("data : ", data.toString())
            if (data.isSuccessful) {
                if (data.body()!!.status == "success") {
                    _responseLogin.value = data.body()!!.response
                } else {
                    _responseLogin.value = emptyList()
                }
            } else {
                Log.e("ERROR : ", data.errorBody().toString())
            }
        }

    }

    fun register(data: RegisterModel) {

        viewModelScope.launch {

            try {
                val data = RetrofitInstance.api.register(data)
                if (data.isSuccessful) {
                    user.value = data.body()?.response
                } else {

                    Log.e("try Again : ", data.message().toString())

                }

            } catch (e: Exception) {

                Log.e("message : ", e.message.toString())
            }


        }
    }


    fun userBank(user_id: String) {
        viewModelScope.launch {

            try {
                val data = RetrofitInstance.api.getAccounts(user_id.toInt())
                if (data.isSuccessful) {
                    userBank.value = data.body()?.response
                } else {

                    Log.e("try Again : ", data.message().toString())

                }

            } catch (e: Exception) {

                Log.e("message : ", e.message.toString())
            }


        }
    }

}