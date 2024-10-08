package com.example.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.MyOrderItemBinding
import com.example.project.model.banners.Response
import com.example.project.network.URL
import com.example.project.network.URL.ImageUrl
import com.squareup.picasso.Picasso


class BannerAdapter(private val listData: ArrayList<Response>) :
    RecyclerView.Adapter<BannerAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {

        return OrderViewHolder(
            MyOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }

    override fun getItemCount() = listData.size


    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

    }

    class OrderViewHolder(private val binding: MyOrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Response) {

            Picasso.get().load(ImageUrl + "assets/image/" + data.image).into(binding.imageBanner)
            binding.dateBanner.text = data.date
        }

    }
}