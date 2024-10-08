package com.example.project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.project.R
import com.example.project.databinding.FragmentAccountBinding
import com.example.project.model.viewModel.UserViewModel
import com.example.project.utils.AuthData

@Suppress("DEPRECATION")
class AccountFragment:Fragment(R.layout.fragment_account) {
    private lateinit var binding: FragmentAccountBinding

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userData(AuthData.user_id)
        binding.UserName.text=AuthData.user_name
        binding.phone.text=AuthData.user_phone
        binding.account.text=AuthData.user_account

        return binding.root
    }


    private fun userData(userData_id: String) {
        viewModel.responseUserBank.observe(this.requireActivity(), Observer { response ->
            AuthData.user_balance = response[0].balance.toString()

            binding.balance.text=response[0].balance.toString()
        })
        viewModel.userBank(userData_id)
    }


}