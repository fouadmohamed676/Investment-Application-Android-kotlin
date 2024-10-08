package com.example.project.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.adapter.InvestDropAdapter
import com.example.project.adapter.InvestmentAdapter
import com.example.project.databinding.FragmentInvestBinding
import com.example.project.model.invesment.Response
import com.example.project.model.invesment.request.RequestInvestment
import com.example.project.model.viewModel.BannersViewModel
import com.example.project.model.viewModel.UserViewModel
import com.example.project.ui.HomeActivity
import com.example.project.utils.AuthData

@Suppress("DEPRECATION")
class InvestFragment : Fragment(R.layout.fragment_invest) {

    private lateinit var binding: FragmentInvestBinding
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var investmentAdapter: InvestmentAdapter
    private lateinit var viewModel: UserViewModel

    private var investID = -1
    private var min_ = -1
    private val bannersList: ArrayList<Response> = ArrayList()
    private val investList: ArrayList<Response> = ArrayList()
    private lateinit var bannersViewModel: BannersViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInvestBinding.inflate(layoutInflater, container, false)
        bannersViewModel = ViewModelProvider(this).get(BannersViewModel::class.java)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.floatingActionButton.setOnClickListener {
            binding.order.visibility = View.VISIBLE
            binding.rec.visibility = View.GONE
        }
        binding.submit.setOnClickListener {
            if (binding.balance.text.isNullOrEmpty()) {
                Toast.makeText(this.requireContext(), "الحقل مطلوب", Toast.LENGTH_SHORT).show()
            } else {

                if (min_ > AuthData.user_balance.toInt()) {
                    Log.e("min", "min: "+min_.toString()+" user balance"+AuthData.user_balance)
                    Toast.makeText(
                        this.requireContext(),
                        "الرجاء التأكد من الرصيد",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    submit()
                }
            }
        }

        setup()
        getData()
        return binding.root
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        bannersList.clear()
        investList.clear()
        bannersViewModel.getResponseInvestment.observe(this.requireActivity(),
            Observer { response ->
                bannersList.addAll(response)
                investList.addAll(response)
                investmentAdapter = InvestmentAdapter(bannersList)
                binding.rec.adapter = investmentAdapter
                investmentAdapter.notifyDataSetChanged()

                binding.spinnerServices.adapter =
                    InvestDropAdapter(this.requireContext(), investList)

                binding.spinnerServices.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {

                        override fun onItemSelected(
                            parent: AdapterView<*>?, view: View?, positoin: Int, id: Long
                        ) {
                            investID = bannersList[binding.spinnerServices.selectedItemPosition].id
                            min_ =
                                bannersList[binding.spinnerServices.selectedItemPosition].minimum_balance

                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                        }
                    }

            })
        bannersViewModel.getInvestments()

    }

    private fun userData(userData_id: String) {
        viewModel.responseUserBank.observe(this.requireActivity(), Observer { response ->
            AuthData.user_balance = response[0].balance.toString()
        })
        viewModel.userBank(userData_id)
    }


    private fun submit() {
        bannersViewModel.getResponse.observe(this.requireActivity(), Observer { response ->
            Log.e("Success", response.toString())
            Toast.makeText(this.requireContext(), "تم الاستثمار بنجاح", Toast.LENGTH_SHORT).show()
            userData(AuthData.user_id)
            activity()
        })

        val data = RequestInvestment(
            investID.toString(), AuthData.user_id, binding.balance.text.toString()
        )
        bannersViewModel.submitInvestment(data)
    }

    private fun activity() {
        val intent = Intent(this.requireContext(), HomeActivity::class.java)
        startActivity(intent)
    }

    private fun setup() {
        layoutManager = LinearLayoutManager(context)
        binding.rec.setHasFixedSize(true)
        binding.rec.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }


}