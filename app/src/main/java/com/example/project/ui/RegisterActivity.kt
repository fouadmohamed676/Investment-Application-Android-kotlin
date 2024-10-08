package com.example.project.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.project.R
import com.example.project.databinding.ActivityRegisterBinding
import com.example.project.model.register.RegisterModel
import com.example.project.model.viewModel.UserViewModel

class RegisterActivity :AppCompatActivity(R.layout.activity_register) {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)


    }



    private fun activity() {
        Toast.makeText(this,"تم تسجيل الحساب بنجاح",Toast.LENGTH_SHORT).show()
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }



    fun submit(view: View) {

        val name =binding.regName.text.toString().trim()
        val phone =binding.regPhone.text.toString().trim()
        val password =binding.regPassword.text.toString().trim()
        viewModel.userData.observe(this, Observer {
                response->
            Log.e("response : ",response.toString())
            activity()
        })
        viewModel.register(RegisterModel(name, phone, password))

    }


}