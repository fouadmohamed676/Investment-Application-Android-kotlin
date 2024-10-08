package com.example.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.BannerItemBinding
import com.example.project.model.invesment.Response

class InvestmentAdapter (private val listData:ArrayList<Response>) :
        RecyclerView.Adapter<InvestmentAdapter.OrderViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {

            return OrderViewHolder(
                BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

            )
        }

        override fun getItemCount() = listData.size


        override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
            val data = listData[position]
            holder.bind(data)

        }

        class OrderViewHolder(private val binding: BannerItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(data: Response) {

                binding.name.text = data.name
                binding.expt.text = data.expected_return.toString()
                binding.min.text = data.minimum_balance.toString()
            }

        }

    }