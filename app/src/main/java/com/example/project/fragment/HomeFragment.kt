package com.example.project.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.adapter.BannerAdapter
import com.example.project.adapter.UserInvestmentAdapter
import com.example.project.databinding.FragmentHomeBinding
import com.example.project.model.banners.Response
import com.example.project.model.viewModel.BannersViewModel
import com.example.project.utils.AuthData

@Suppress("DEPRECATION")
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bannersViewModel: BannersViewModel
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val bannersList: ArrayList<Response> = ArrayList()
    private val investList: ArrayList<com.example.project.model.invesment.op.Response> = ArrayList()
    private lateinit var bannersAdapter: BannerAdapter
    private lateinit var userInvestmentAdapter: UserInvestmentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        bannersViewModel = ViewModelProvider(this).get(BannersViewModel::class.java)
        setup()
        getBannersData()
        getInvestUser()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getBannersData() {
        bannersList.clear()
        bannersViewModel.getResponseBanners.observe(this.requireActivity(), Observer { response ->
            bannersList.addAll(response)

            Log.e("List Banners", bannersList.toString())
            bannersAdapter = BannerAdapter(bannersList)
            binding.bannersR.adapter = bannersAdapter
            bannersAdapter.notifyDataSetChanged()
            Log.e("User Banners", response.toString())
        })
        bannersViewModel.getBanners()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getInvestUser() {
        investList.clear()
        bannersViewModel.getResponseInvestmentUser.observe(
            this.requireActivity(),
            Observer { response ->
                investList.addAll(response)
                if (investList.isEmpty()) {
                    binding.image.visibility = View.VISIBLE
                }
                else
                {
                    binding.image.visibility=View.GONE
                }
                Log.e("List invest", investList.toString())
                userInvestmentAdapter = UserInvestmentAdapter(investList)
                binding.invests.adapter = userInvestmentAdapter
                userInvestmentAdapter.notifyDataSetChanged()
            })
        bannersViewModel.getInvestmentsOp(AuthData.user_id.toInt())

    }


    private fun setup() {
        layoutManager = LinearLayoutManager(context)
        binding.invests.setHasFixedSize(true)
        binding.bannersR.setHasFixedSize(true)
        binding.invests.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.bannersR.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

    }
}