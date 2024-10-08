package com.example.project.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.UserInvestsBinding
import com.example.project.model.invesment.op.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

class UserInvestmentAdapter (private val listData:ArrayList<Response>) :
    RecyclerView.Adapter<UserInvestmentAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {

        return OrderViewHolder(
            UserInvestsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }

    override fun getItemCount() = listData.size


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

    }

    class OrderViewHolder(private val binding: UserInvestsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: Response) {

            binding.name.text = data.investment.name
            binding.expt.text = data.investment.expected_return.toString()
            binding.min.text  = data.created_at
        }

    }

}