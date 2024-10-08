package com.example.project.model.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.model.banners.Response
import com.example.project.model.invesment.request.RequestInvestment
import com.example.project.network.RetrofitInstance
import kotlinx.coroutines.launch

class BannersViewModel : ViewModel() {

    private val _responseBanners: MutableLiveData<List<Response>> = MutableLiveData()
    val getResponseBanners: LiveData<List<Response>>
        get() = _responseBanners

    private val _responseInvestment: MutableLiveData<com.example.project.model.invesment.respones.Response?> =
        MutableLiveData()
    val getResponse: LiveData<com.example.project.model.invesment.respones.Response?>
        get() = _responseInvestment

    private val _responseInvestments: MutableLiveData<List<com.example.project.model.invesment.Response>> =
        MutableLiveData()
    val getResponseInvestment: LiveData<List<com.example.project.model.invesment.Response>>
        get() = _responseInvestments

    private val _responseInvestmentUser: MutableLiveData<List<com.example.project.model.invesment.op.Response>> =
        MutableLiveData()
    val getResponseInvestmentUser: LiveData<List<com.example.project.model.invesment.op.Response>>
        get() = _responseInvestmentUser

    fun getBanners() {
        viewModelScope.launch {
            val data = RetrofitInstance.api.banners()
            if (data.isSuccessful) {
                try {
                    _responseBanners.value = data.body()!!.response
                    Log.e("response VM: ", data.body()!!.response.toString())
                } catch (e: Exception) {

                    Log.e("Message : ", e.message.toString())

                }
            } else {

                Log.e("Error : ", data.errorBody().toString())
            }
        }

    }

    fun getInvestments() {
        viewModelScope.launch {
            val data = RetrofitInstance.api.investments()
            if (data.isSuccessful) {
                try {
                    _responseInvestments.value = data.body()!!.response
                    Log.e("response VM: ", data.body()!!.response.toString())
                } catch (e: Exception) {

                    Log.e("Message : ", e.message.toString())

                }
            } else {

                Log.e("Error : ", data.errorBody().toString())
            }
        }
    }

    fun getInvestmentsOp(user_id :Int) {
        viewModelScope.launch {
            val data = RetrofitInstance.api.getInvestmentUser(user_id)
            if (data.isSuccessful) {
                try {
                    _responseInvestmentUser.value = data.body()!!.response
                    Log.e("response VM: ", data.body()!!.response.toString())
                } catch (e: Exception) {
                    Log.e("Message : ", e.message.toString())
                }
            } else {

                Log.e("Error : ", data.errorBody().toString())
            }
        }
    }


    fun submitInvestment(data: RequestInvestment) {
        viewModelScope.launch {
            val response = RetrofitInstance.api.create(data)
            if (response.isSuccessful) {
                Log.e("----: ", response.body()!!.response.toString())

                try {
                    if (response.body()!!.response.equals("success")) {
                        _responseInvestment.value = response.body()!!.response
                        Log.e("response VM: ", response.body()!!.response.toString())
                    } else {
                        _responseInvestment.value = null
                        Log.e("vm null response : ", response.body()!!.response.toString())
                    }
                } catch (e: Exception) {
                    Log.e("Message : ", e.message.toString())
                }
            } else {
                Log.e("Error : ", response.errorBody().toString())
                Log.e("message : ", response.message().toString())
            }
        }

    }


}