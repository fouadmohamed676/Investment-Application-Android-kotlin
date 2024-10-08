package com.example.project.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.project.R
import com.example.project.databinding.ActivityMainBinding
import com.example.project.model.login.request.LoginRequest
import com.example.project.model.viewModel.UserViewModel
import com.example.project.utils.AuthData
import com.example.project.utils.AuthData.user_id
import com.example.project.utils.AuthData.user_name
import com.example.project.utils.AuthData.user_phone

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        progressDialog = ProgressDialog(this)
        binding.login.setOnClickListener {
            if (binding.phone.text.isNullOrEmpty()) {
                Toast.makeText(this, " الحقل مطلوب", Toast.LENGTH_SHORT).show()
            } else {
                starProgress()
                login()
            }

        }
        binding.register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }


    private fun login() {

        viewModel.getResponseLogin.observe(this, Observer { response ->
            try {
                stopProgress()
                if (response.isNullOrEmpty()) {
                    Toast.makeText(this, "تأكد من  رقم الهاتف", Toast.LENGTH_SHORT).show()
                    Log.e("Phone fail", response.toString())
                } else {
                    user_id = response[0].id.toString()
                    user_name = response[0].name
                    user_phone = response[0].phone
                    userData(user_id)
                    activity()
                    Log.e("login success : ", response.toString())
                }
            } catch (e: Exception) {

                Log.e("login message : ", e.toString())
            }
        })

        val phone = binding.phone.text.toString().trim()
        val data = LoginRequest(phone)
        viewModel.loginUser(data)
    }



    private fun userData(userData_id: String) {
        viewModel.responseUserBank.observe(this, Observer {
                response ->
            AuthData.user_account=response[0].account
            AuthData.user_balance=response[0].balance.toString()
            Log.e("User Account Bank", response.toString())
        })
        viewModel.userBank(userData_id)
    }



    private fun activity() {

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


    private fun starProgress() {
        progressDialog.setMessage("جاري تسجيل الدخول.. ")
        progressDialog.show()
        progressDialog.setCancelable(false)
    }

    private fun stopProgress() {
        progressDialog.dismiss()
    }

    private var doubleBackToExitPressedOnce: Boolean = false

    @Suppress("OVERRIDE_DEPRECATION")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finish()
            finishAffinity()
            super.finish()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "إضغط مره اخرى للخروج", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }


}